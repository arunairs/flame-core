package cn.blinkmind.flame.server.service;

import cn.blinkmind.flame.server.exception.Errors;
import cn.blinkmind.flame.server.repository.BranchRepository;
import cn.blinkmind.flame.server.repository.entity.Branch;
import cn.blinkmind.flame.server.repository.entity.Commit;
import cn.blinkmind.flame.server.repository.entity.Push;
import cn.blinkmind.flame.server.repository.entity.Snapshot;
import cn.blinkmind.flame.server.repository.entity.User;
import cn.blinkmind.flame.server.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PushService
{
    @Autowired
    private BranchService branchService;

    @Autowired
    private SnapshotService snapshotService;

    public void push(final Long documentId, final Push rawData, final User user)
    {
        Assert.isTrue(rawData.getSnapshot() != null && rawData.getSnapshot().getId() != null, Errors.PUSH_SNAPSHOT_IS_NOT_SPECIFIED);
        Snapshot snapshot = snapshotService.require(rawData.getSnapshot().getId(), user, Errors.SNAPSHOT_IS_NOT_FOUND);

        Branch branch = snapshot.getBranch();
        Assert.notNull(branch, Errors.BRANCH_IS_NOT_FOUND);
        Assert.equals(branch.getDocumentRef().getId(), documentId, Errors.BRANCH_NOT_MATCHES_DOCUMENT);

        Assert.isTrue(snapshot.getHeaders().getLong(Commit.VERSION) >= branch.getHeaders().getLong(Commit.VERSION), Errors.SNAPSHOT_IS_OUTDATED);
        Branch result = branchService.updateArchive(branch, snapshot, user);
        Assert.notNull(result, Errors.SNAPSHOT_IS_OUTDATED);

        snapshotService.updateHeaders(snapshot, result.getHeaders());
    }
}

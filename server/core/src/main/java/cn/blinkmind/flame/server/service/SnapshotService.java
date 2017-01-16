package cn.blinkmind.flame.server.service;

import cn.blinkmind.flame.server.util.patch.JsonPatch;
import cn.blinkmind.flame.server.util.Assert;
import cn.blinkmind.flame.server.exception.Error;
import cn.blinkmind.flame.server.exception.Errors;
import cn.blinkmind.flame.server.repository.SnapshotRepository;
import cn.blinkmind.flame.server.repository.entity.Archive;
import cn.blinkmind.flame.server.repository.entity.Branch;
import cn.blinkmind.flame.server.repository.entity.Snapshot;
import cn.blinkmind.flame.server.repository.entity.User;
import cn.blinkmind.flame.server.repository.exception.ResourceNotFoundException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SnapshotService extends AbstractPersistenceService
{
    @Autowired
    private SnapshotRepository snapshotRepository;

    @Autowired
    private BranchService branchService;

    public Snapshot create(long branchId, Snapshot rawData, User creator)
    {
        Branch branch = branchService.require(branchId, creator, Errors.BRANCH_IS_NOT_FOUND);
        Assert.notBlank(rawData.getName(), Errors.SNAPSHOT_NAME_IS_BLANK);
        Assert.isFalse(snapshotRepository.exists(rawData.getName(), branch.getId(), creator), Errors.RESOURCE_ALREADY_EXISTS);

        Snapshot snapshot = new Snapshot();
        snapshot.setId(newId());
        snapshot.setCreator(creator);
        snapshot.setName(rawData.getName());
        snapshot.setBranch(branch);
        snapshot.setArchive(branchService.getArchive(branch.getId(), creator));
        snapshotRepository.insert(snapshot);
        return snapshot;
    }

    public Snapshot get(long id, User user)
    {
        return snapshotRepository.get(id);
    }

    public Snapshot require(long id, User user)
    {
        Snapshot snapshot = snapshotRepository.require(id);
        return snapshot;
    }

    public Snapshot require(long id, User user, Error error)
    {
        try
        {
            Snapshot snapshot = require(id, user);
            return snapshot;
        }
        catch (ResourceNotFoundException e)
        {
            Error.occurs(error);
            return null;
        }
    }

    public Snapshot get(Branch branch, User user)
    {
        Snapshot snapshot = snapshotRepository.get(branch, user);
        return snapshot;
    }

    public Snapshot require(Branch branch, User user)
    {
        Snapshot snapshot = get(branch, user);
        Assert.notNull(snapshot, Errors.RESOURCE_NOT_FOUND);
        return snapshot;
    }

    public Snapshot patch(long id, final Map<String, Object> rawData, final User user)
    {
        Snapshot snapshot = this.require(id, user);
        JsonPatch.on(snapshot)
                .mappedBy(rawData)
                .fields("name")
                .apply();
        snapshotRepository.update(snapshot);
        return snapshot;
    }

    public void delete(long id, User user)
    {
        snapshotRepository.delete(id);
    }

    public void updateArchive(long snapshotId, Archive archive, User user)
    {
        if (CollectionUtils.isNotEmpty(archive.getModules()))
        {
            archive.getModules().forEach(module ->
            {
                if (module.getId() == null) module.setId(newId());
                if (CollectionUtils.isNotEmpty(module.getApis()))
                {
                    module.getApis().stream().filter(api -> api.getId() == null)
                            .forEach(api -> api.setId(newId()));
                }
            });
        }
        Snapshot snapshot = snapshotRepository.updateArchive(snapshotId, archive, user);
        Assert.notNull(snapshot, Errors.RESOURCE_NOT_FOUND);
    }
}

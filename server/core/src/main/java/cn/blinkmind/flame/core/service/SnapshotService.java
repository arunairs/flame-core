package cn.blinkmind.flame.core.service;

import cn.blinkmind.flame.core.common.util.Assert;
import cn.blinkmind.flame.core.common.patch.JsonPatch;
import cn.blinkmind.flame.core.bean.Diffs;
import cn.blinkmind.flame.core.exception.Errors;
import cn.blinkmind.flame.repository.SnapshotRepository;
import cn.blinkmind.flame.repository.entity.*;
import cn.blinkmind.flame.repository.util.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class SnapshotService {
    private IdGenerator<Long> idGenerator;
    private SnapshotRepository snapshotRepository;
    private ArchiveService archiveService;
    private BranchService branchService;

    @Autowired
    public SnapshotService(IdGenerator<Long> idGenerator, SnapshotRepository snapshotRepository, ArchiveService archiveService, BranchService branchService) {
        this.idGenerator = idGenerator;
        this.snapshotRepository = snapshotRepository;
        this.archiveService = archiveService;
        this.branchService = branchService;
    }

    public Snapshot create(final Long branchId, final Snapshot snapshotData, final User creator) {
        Branch branch = branchService.get(branchId, creator);
        Assert.isNotNull(branch, Errors.BRANCH_IS_NOT_FOUND);
        Assert.isNotBlank(snapshotData.getName(), Errors.SNAPSHOT_NAME_IS_BLANK);
        Assert.isFalse(snapshotRepository.exists(snapshotData.getName(), branch.getId(), creator), Errors.RESOURCE_ALREADY_EXISTS);

        Snapshot snapshot = new Snapshot();
//        snapshot.setCreator(creator);
        snapshot.setName(snapshotData.getName());
//        snapshot.setBranch(branch);
        snapshot.getHeaders().putAll(branch.getHeaders());
        snapshot.setArchive(branchService.getArchive(branch.getId(), creator));
        snapshotRepository.insert(snapshot);
        return snapshot;
    }

    public Snapshot get(final Long id, final User user) {
        return snapshotRepository.get(id);
    }

    public Snapshot get(final Branch branch, final User user) {
        Snapshot snapshot = snapshotRepository.get(branch, user);
        return snapshot;
    }

    public Snapshot patch(final Long id, final Map<String, Object> snapshotData, final User user) {
        Snapshot snapshot = this.get(id, user);
        Assert.isNotNull(snapshot, Errors.RESOURCE_NOT_FOUND);
        JsonPatch.on(snapshot)
                .mappedBy(snapshotData)
                .fields("name")
                .apply();
        snapshotRepository.update(snapshot);
        return snapshot;
    }

    public boolean exists(final Long id, final Long branchId) {
        return snapshotRepository.exists(id, branchId);
    }

    public void delete(final Long id, final User user) {
        snapshotRepository.delete(id);
    }

    public void updateArchive(final Long snapshotId, Archive archive, final User user) {
        Snapshot snapshot = this.get(snapshotId, user);
        Assert.isNotNull(snapshot, Errors.RESOURCE_NOT_FOUND);
        if (Diffs.isEmpty(archiveService.diff(snapshot.getArchive(), archive)))
            return;

        snapshot.getHeaders().add(Commit.SN, UUID.randomUUID().toString().replace("-", "").substring(0, 8));
        Optional.of(archive.getModules()).ifPresent(modules -> modules.forEach(module ->
        {
            //TODO: assign id to modules & apis
//                if (module.getId() == null) module.setId(idGenerator.nextId());
//                if (CollectionUtils.isNotEmpty(module.getApis())) {
//                    module.getApis().stream().filter(api -> api.getId() == null)
//                            .forEach(api -> api.setId(idGenerator.nextId()));
//                }
        }));
        Assert.isNotNull(snapshotRepository.updateArchive(snapshotId, snapshot.getHeaders(), archive), Errors.RESOURCE_NOT_FOUND);
    }

    private void updateHeaders(final Snapshot snapshot, final Headers headers) {
        snapshotRepository.updateHeaders(snapshot.getId(), headers);
    }

    public void push(final Long documentId, final Snapshot snapshotData, final User user) {
        Assert.isTrue(snapshotData != null && snapshotData.getId() != null, Errors.SNAPSHOT_IS_NOT_SPECIFIED);
        Snapshot snapshot = this.get(snapshotData.getId(), user);
        Assert.isNotNull(snapshot, Errors.SNAPSHOT_IS_NOT_FOUND);

        //Branch branch = snapshot.getBranch();
        //Assert.isNotNull(branch, Errors.BRANCH_IS_NOT_FOUND);
        //Assert.equals(branch.getDocumentRef().getId(), documentId, Errors.BRANCH_NOT_MATCHES_DOCUMENT);

        //Branch result = branchService.updateArchive(branch, snapshot, user);
        //Assert.isNotNull(result, Errors.SNAPSHOT_IS_OUTDATED);

        //this.updateHeaders(snapshot, result.getHeaders());
    }

    public void pull(final Long documentId, final Snapshot snapshotData, final User user) {
        Assert.isTrue(snapshotData != null && snapshotData.getId() != null, Errors.SNAPSHOT_IS_NOT_SPECIFIED);
        Snapshot snapshot = this.get(snapshotData.getId(), user);
        Assert.isNotNull(snapshot, Errors.SNAPSHOT_IS_NOT_FOUND);

        //Branch branch = snapshot.getBranch();
//        Assert.isNotNull(branch, Errors.BRANCH_IS_NOT_FOUND);
//        Assert.equals(branch.getDocumentRef().getId(), documentId, Errors.BRANCH_NOT_MATCHES_DOCUMENT);
//
//        if (snapshot.getHeaders().getLong(Commit.VERSION) >= branch.getHeaders().getLong(Commit.VERSION)) return;
//        archiveService.diff(snapshot.getArchive(), branch.getArchive());
    }
}

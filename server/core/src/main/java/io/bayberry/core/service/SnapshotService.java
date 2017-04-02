package io.bayberry.core.service;

import io.bayberry.core.bean.Diffs;
import io.bayberry.core.common.patch.JsonPatch;
import io.bayberry.core.exception.Errors;
import io.bayberry.repository.SnapshotRepository;
import io.bayberry.core.common.validation.Validator;
import io.bayberry.repository.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static io.bayberry.core.common.validation.Matcher.*;
import static io.bayberry.core.common.validation.Validator.orElseThrow;
import static io.bayberry.core.common.validation.Validator.validateThat;

@Service
public class SnapshotService {
    private final SnapshotRepository snapshotRepository;
    private final ArchiveService archiveService;
    private final BranchService branchService;

    @Autowired
    public SnapshotService(SnapshotRepository snapshotRepository,
                           ArchiveService archiveService,
                           BranchService branchService) {
        this.snapshotRepository = snapshotRepository;
        this.archiveService = archiveService;
        this.branchService = branchService;
    }

    public Snapshot create(final Long branchId, final Snapshot snapshotData, final User creator) {
        Branch branch = branchService.get(branchId, creator).orElseThrow(() -> Errors.BRANCH_IS_NOT_FOUND);
        validateThat(snapshotData.getName(), not(blank()), orElseThrow(Errors.SNAPSHOT_NAME_IS_BLANK))
                .and(snapshotRepository.exists(snapshotData.getName(), branch.getId(), creator),
                        orElseThrow(Errors.RESOURCE_ALREADY_EXISTS));

        Snapshot snapshot = new Snapshot();
//        snapshot.setCreator(creator);
        snapshot.setName(snapshotData.getName());
//        snapshot.setBranch(branch);
        snapshot.getHeaders().putAll(branch.getHeaders());
        snapshot.setArchive(branch.getArchive());
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
        Validator.validateThat(snapshot, not(nil()), () -> {
            throw Errors.RESOURCE_NOT_FOUND;
        });
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
        Validator.validateThat(snapshot, not(nil()), () -> {
            throw Errors.RESOURCE_NOT_FOUND;
        });
        if (Diffs.isEmpty(archiveService.diff(snapshot.getArchive(), archive)))
            return;

        snapshot.getHeaders().put(Commit.SN, UUID.randomUUID().toString().replace("-", "").substring(0, 8));
        Optional.of(archive.getModules()).ifPresent(modules -> modules.forEach(module ->
        {
            //TODO: assign id to modules & apis
//                if (module.getId() == null) module.setId(idGenerator.nextId());
//                if (CollectionUtils.isNotEmpty(module.getApis())) {
//                    module.getApis().stream().filter(api -> api.getId() == null)
//                            .forEach(api -> api.setId(idGenerator.nextId()));
//                }
        }));
        Validator.validateThat(snapshotRepository.updateArchive(snapshotId, snapshot.getHeaders(), archive), not(nil()), () -> {
            throw Errors.RESOURCE_NOT_FOUND;
        });
    }

    private void updateHeaders(final Snapshot snapshot, final Headers headers) {
        snapshotRepository.updateHeaders(snapshot.getId(), headers);
    }

    public void push(final Long documentId, final Long snapshotId, final User user) {
        Validator.validateThat(snapshotId, not(nil()), () -> {
            throw Errors.SNAPSHOT_IS_NOT_SPECIFIED;
        });

        Snapshot snapshot = this.get(snapshotId, user);
        Validator.validateThat(snapshot, not(nil()), () -> {
            throw Errors.SNAPSHOT_IS_NOT_FOUND;
        });

        //Branch branch = snapshot.getBranch();
        //Validator.isNotNull(branch, Errors.BRANCH_IS_NOT_FOUND);
        //Validator.equals(branch.getDocumentRef().getId(), documentId, Errors.BRANCH_NOT_MATCHES_DOCUMENT);

        //Branch result = branchService.updateArchive(branch, snapshot, user);
        //Validator.isNotNull(result, Errors.SNAPSHOT_IS_OUTDATED);

        //this.updateHeaders(snapshot, result.getHeaders());
    }

    public void pull(final Long documentId, final Long snapshotId, final User user) {
        Validator.validateThat(snapshotId, not(nil()), () -> {
            throw Errors.SNAPSHOT_IS_NOT_SPECIFIED;
        });
        Snapshot snapshot = this.get(snapshotId, user);
        Validator.validateThat(snapshot, not(nil()), () -> {
            throw Errors.SNAPSHOT_IS_NOT_FOUND;
        });

        //Branch branch = snapshot.getBranch();
//        Validator.isNotNull(branch, Errors.BRANCH_IS_NOT_FOUND);
//        Validator.equals(branch.getDocumentRef().getId(), documentId, Errors.BRANCH_NOT_MATCHES_DOCUMENT);
//
//        if (snapshot.getHeaders().getLong(Commit.VERSION) >= branch.getHeaders().getLong(Commit.VERSION)) return;
//        archiveService.diff(snapshot.getArchive(), branch.getArchive());
    }
}

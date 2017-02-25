package cn.blinkmind.flame.core.service;

import cn.blinkmind.flame.core.common.util.Assert;
import cn.blinkmind.flame.core.common.patch.JsonPatch;
import cn.blinkmind.flame.core.exception.Errors;
import cn.blinkmind.flame.repository.BranchRepository;
import cn.blinkmind.flame.repository.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private DocumentService documentService;

    public Branch get(final Long id, final User user) {
        return branchRepository.get(id);
    }

    public Branch get(final Branch entity, final User user) {
        return branchRepository.get(entity);
    }

    public Branch create(final Long documentId, final Branch rawData, final User creator) {
        Document document = new Document();
                //documentService.get(documentId, creator);
        Assert.isNotNull(document, Errors.DOCUMENT_IS_NOT_FOUND);
        Assert.isNotBlank(rawData.getName(), Errors.BRANCH_NAME_IS_BLANK);
        Assert.isFalse(branchRepository.exists(rawData.getName(), documentId), Errors.RESOURCE_ALREADY_EXISTS);

        Branch origin = null;
//        if (rawData.getOrigin() != null && rawData.getOrigin().getId() != null) {
//            origin = this.get(rawData.getOrigin(), creator);
//            Assert.isNotNull(origin, Errors.BRANCH_ORIGIN_IS_NOT_FOUND);
//            origin.setArchive(this.getArchive(origin.getId(), creator));
//        }

        Branch branch = new Branch();
        branch.setName(rawData.getName());
        //branch.setCreator(creator);
        branch.getHeaders().add(Commit.VERSION, 0L).add(Commit.SN, null);
        //branch.setDocument(document);
        //branch.setOrigin(origin);
        branch.setArchive(origin != null && origin.getArchive() != null ? origin.getArchive() : null);
        branchRepository.insert(branch);
        return branch;
    }

    public boolean exists(final Long id, final Long documentId) {
        return branchRepository.exists(id, documentId);
    }

    public void delete(final Long id, final User user) {
        branchRepository.delete(id);
    }

    public Branch patch(final Long id, final Map<String, Object> rawData, final User user) {
        Branch branch = this.get(id, user);
        Assert.isNotNull(branch, Errors.RESOURCE_NOT_FOUND);
        JsonPatch.on(branch)
                .mappedBy(rawData)
                .fields("name")
                .apply();
        branchRepository.update(branch);
        return branch;
    }

    public Archive getArchive(final Long branchId, final User user) {
        return branchRepository.getArchive(branchId);
    }

    public Branch updateArchive(final Branch branch, final Commit<Archive> commit, final User user) {
        Assert.isTrue(commit.getHeaders().getLong(Commit.VERSION) >= branch.getHeaders().getLong(Commit.VERSION), Errors.SNAPSHOT_IS_OUTDATED);
        if (Commit.equals(commit, branch)) return branch;
        return branchRepository.updateArchive(branch.getId(), commit, user);
    }
}

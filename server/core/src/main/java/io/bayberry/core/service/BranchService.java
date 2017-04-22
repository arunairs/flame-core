package io.bayberry.core.service;

import io.bayberry.core.authentication.Auth;
import io.bayberry.core.exception.Errors;
import io.bayberry.repository.BranchRepository;
import io.bayberry.repository.model.Branch;
import io.bayberry.repository.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BranchService {

    private final BranchRepository branchRepository;

    @Autowired
    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    public Optional<Branch> get(Long id, Auth auth) {
        return Optional.ofNullable(branchRepository.get(id));
    }

    public Branch create(Branch branch, Long documentId, Auth auth) {
        if (branchRepository.exists(branch.getName(), documentId)) {
            throw Errors.RESOURCE_ALREADY_EXISTS;
        }

        Branch output = new Branch();
        output.setName(branch.getName());
        output.setCreatorId(auth.getUserId());
        output.setDocumentId(documentId);
        if (branch.getOriginId() != null) {
            Branch origin = this.get(branch.getOriginId(), auth)
                    .orElseThrow(() -> Errors.BRANCH_ORIGIN_IS_NOT_FOUND);
            output.setOriginId(origin.getId());
            output.setArchive(origin.getArchive());
            output.getHeader().putAll(origin.getHeader());
        }
        branchRepository.insert(output);
        return output;
    }

    public void delete(Long id, Auth auth) {
        branchRepository.delete(id);
    }

    public Branch update(Branch branch, Auth auth) {
        if (!this.exists(branch.getId())) throw Errors.RESOURCE_NOT_FOUND;
        return branchRepository.updateAndReturn(branch);
    }

    public boolean exists(Long id) {
        return branchRepository.exists(id);
    }
}

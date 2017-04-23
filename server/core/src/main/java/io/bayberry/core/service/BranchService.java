package io.bayberry.core.service;

import io.bayberry.core.authentication.User;
import io.bayberry.core.exception.Errors;
import io.bayberry.repository.BranchRepository;
import io.bayberry.repository.model.Archive;
import io.bayberry.repository.model.Branch;
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

    public Optional<Branch> get(Long id, User user) {
        return Optional.ofNullable(branchRepository.get(id));
    }

    public Branch create(Branch branch, Long documentId, User user) {
        if (branchRepository.exists(branch.getName(), documentId)) {
            throw Errors.RESOURCE_ALREADY_EXISTS;
        }

        branch.setCreatorId(user.getId());
        branch.setDocumentId(documentId);
        if (branch.getOriginId() != null) {
            Branch origin = this.get(branch.getOriginId(), user)
                    .orElseThrow(() -> Errors.BRANCH_ORIGIN_IS_NOT_FOUND);
            branch.setArchive(origin.getArchive());
            branch.getHeader().putAll(origin.getHeader());
        } else {
            branch.setArchive(new Archive());
        }
        branchRepository.insert(branch);
        return branch;
    }

    public void delete(Long id, User user) {
        branchRepository.delete(id);
    }

    public Branch update(Branch branch, User user) {
        if (!this.exists(branch.getId())) throw Errors.RESOURCE_NOT_FOUND;
        return branchRepository.updateAndReturn(branch);
    }

    public boolean exists(Long id) {
        return branchRepository.exists(id);
    }
}

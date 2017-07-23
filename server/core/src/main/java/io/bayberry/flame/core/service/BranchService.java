package io.bayberry.flame.core.service;

import io.bayberry.flame.core.authentication.User;
import io.bayberry.flame.core.exception.BranchConflictException;
import io.bayberry.flame.core.exception.BranchNotFoundException;
import io.bayberry.flame.core.repository.BranchRepository;
import io.bayberry.flame.core.repository.entity.Archive;
import io.bayberry.flame.core.repository.entity.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BranchService {

    private final BranchRepository branchRepository;

    @Autowired
    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    public Branch create(Branch branch, User user) {
        if (branchRepository.exists(branch.getDocumentId(), branch.getName())) {
            throw new BranchConflictException();
        }
        branch.setCreatorId(user.getId());
        if (branch.hasOrigin()) {
            Branch origin = this.get(branch.getOriginId(), user);
            this.copyPropertiesFromOrigin(branch, origin);
        } else {
            branch.setArchive(new Archive());
        }
        branchRepository.insert(branch);
        return branch;
    }

    private void copyPropertiesFromOrigin(Branch branch, Branch origin) {
        branch.setArchive(origin.getArchive());
        branch.getHeader().putAll(origin.getHeader());
    }

    public Branch get(Long id, User user) {
        return branchRepository.get(id).orElseThrow(BranchNotFoundException::new);
    }

    public void delete(Long id, User user) {
        branchRepository.delete(id);
    }

    public void update(Branch branch, User user) {
        branch.setLastModifierId(user.getId());
        if (branchRepository.update(branch) == 0) {
            throw new BranchNotFoundException();
        }
    }
}

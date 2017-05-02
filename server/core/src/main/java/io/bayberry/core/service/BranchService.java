package io.bayberry.core.service;

import io.bayberry.core.authentication.User;
import io.bayberry.core.common.Error;
import io.bayberry.core.common.Result;
import io.bayberry.repository.BranchRepository;
import io.bayberry.repository.model.Archive;
import io.bayberry.repository.model.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BranchService {

    private final BranchRepository branchRepository;

    @Autowired
    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    public Result<Branch, Error> create(Branch branch, User user) {
        if (branchRepository.exists(branch.getDocumentId(), branch.getName())) {
            return Result.fail(Error.BRANCH_ALREADY_EXISTS);
        }
        branch.setCreatorId(user.getId());
        if (branch.hasOrigin()) {
            Result<Branch, Error> originResult = this.get(branch.getOriginId(), user);
            if (originResult.hasErrors()) return Result.fail(originResult.getError());
            this.copyPropertiesFromOrigin(branch, originResult.getValue());
        } else {
            branch.setArchive(new Archive());
        }
        branchRepository.insert(branch);
        return Result.ok(branch);
    }

    public Result<Branch, Error> get(Long id, User user) {
        Branch branch = branchRepository.get(id);
        return Result.failIfNull(branch, Error.BRANCH_NOT_FOUND);
    }

    public void delete(Long id, User user) {
        branchRepository.remove(id);
    }

    public Result<Branch, Error> update(Branch branch, User user) {
        return Result.failIfNull(branchRepository.update(branch), Error.BRANCH_NOT_FOUND);
    }

    private void copyPropertiesFromOrigin(Branch branch, Branch origin) {
        branch.setArchive(origin.getArchive());
        branch.getHeader().putAll(origin.getHeader());
    }
}

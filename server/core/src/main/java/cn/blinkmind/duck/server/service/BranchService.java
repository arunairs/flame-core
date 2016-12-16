package cn.blinkmind.duck.server.service;

import cn.blinkmind.duck.server.exception.Assertion;
import cn.blinkmind.duck.server.exception.Error;
import cn.blinkmind.duck.server.exception.Errors;
import cn.blinkmind.duck.server.repository.BranchRepository;
import cn.blinkmind.duck.server.repository.entity.Archive;
import cn.blinkmind.duck.server.repository.entity.Branch;
import cn.blinkmind.duck.server.repository.entity.Document;
import cn.blinkmind.duck.server.repository.entity.User;
import cn.blinkmind.duck.server.repository.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BranchService {
    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private RepositoryService repositoryService;

    public Branch get(long id, User user) {
        return branchRepository.get(id);
    }

    public Branch require(long id, User user) {
        Branch branch = branchRepository.require(id);
        return branch;
    }

    public Branch require(long id, User user, Error error) {
        try {
            Branch branch = require(id, user);
            return branch;
        } catch (ResourceNotFoundException e) {
            Error.occurs(error);
            return null;
        }
    }

    public Branch create(Branch branch, Document document, User creator) {
        Assertion.isFalse(document == null || document.getId() == null, Errors.BRANCH_DOCUMENT_IS_NOT_SPECIFIED);
        Assertion.notBlank(branch.getName(), Errors.BRANCH_NAME_IS_BLANK);

        branch.setId(repositoryService.newId());
        branch.setCreator(creator);
        branch.setDocument(document);
        branch.setArchive(new Archive());
        branchRepository.insert(branch);
        return branch;
    }

    public void delete(Branch branch, User user) {
        branchRepository.delete(branch);
    }
}

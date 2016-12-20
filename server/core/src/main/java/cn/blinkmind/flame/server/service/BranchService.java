package cn.blinkmind.flame.server.service;

import cn.blinkmind.flame.server.exception.Assertion;
import cn.blinkmind.flame.server.exception.Error;
import cn.blinkmind.flame.server.exception.Errors;
import cn.blinkmind.flame.server.repository.BranchRepository;
import cn.blinkmind.flame.server.repository.entity.Archive;
import cn.blinkmind.flame.server.repository.entity.Branch;
import cn.blinkmind.flame.server.repository.entity.Document;
import cn.blinkmind.flame.server.repository.entity.User;
import cn.blinkmind.flame.server.repository.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BranchService extends PersistenceService
{
    @Autowired
    private BranchRepository branchRepository;

    public Branch get(long id, User user)
    {
        return branchRepository.get(id);
    }

    public Branch require(long id, User user)
    {
        Branch branch = branchRepository.require(id);
        return branch;
    }

    public Branch require(long id, User user, Error error)
    {
        try
        {
            Branch branch = require(id, user);
            return branch;
        }
        catch (ResourceNotFoundException e)
        {
            Error.occurs(error);
            return null;
        }
    }

    public Branch create(Branch branch, Document document, User creator)
    {
        Assertion.isFalse(document == null || document.getId() == null, Errors.BRANCH_DOCUMENT_IS_NOT_SPECIFIED);
        Assertion.notBlank(branch.getName(), Errors.BRANCH_NAME_IS_BLANK);

        branch.setId(newId());
        branch.setCreator(creator);
        branch.setDocument(document);
        branch.setArchive(new Archive());
        branchRepository.insert(branch);
        return branch;
    }

    public void delete(long id, User user)
    {
        branchRepository.delete(id);
    }
}

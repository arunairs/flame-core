package cn.blinkmind.flame.server.service;

import cn.blinkmind.flame.server.bean.patch.JSONPatch;
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

import java.util.Map;

@Service
public class BranchService extends AbstractPersistenceService
{
    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private DocumentService documentService;

    public Branch get(long id, User user)
    {
        return branchRepository.get(id);
    }

    public Branch get(Branch entity, User user)
    {
        return branchRepository.get(entity);
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
            return require(id, user);
        }
        catch (ResourceNotFoundException e)
        {
            Error.occurs(error);
        }
        return null;
    }

    public Branch create(long documentId, Branch rawData, User creator)
    {
        Document document = documentService.get(documentId, creator);
        Assertion.notNull(document, Errors.DOCUMENT_IS_NOT_FOUND);
        Assertion.notBlank(rawData.getName(), Errors.BRANCH_NAME_IS_BLANK);

        Branch source = this.get(rawData.getSource(), creator);
        if (source != null)
        {
            source.setArchive(this.getArchive(source.getId(), creator));
        }

        Branch branch = new Branch();
        branch.setId(newId());
        branch.setName(rawData.getName());
        branch.setCreator(creator);
        branch.setDocument(document);
        branch.setSource(source);
        branch.setArchive(source != null && source.getArchive() != null ? source.getArchive() : new Archive());
        branchRepository.insert(branch);
        return branch;
    }

    public void delete(long id, User user)
    {
        branchRepository.delete(id);
    }

    public Branch patch(long id, final Map<String, Object> rawData, final User user)
    {
        Branch branch = this.require(id, user);
        JSONPatch.on(branch)
                .mappedBy(rawData)
                .fields("name")
                .apply();
        branchRepository.update(branch);
        return branch;
    }

    public Archive getArchive(Long branchId, User user)
    {
        return branchRepository.getArchive(branchId);
    }

}

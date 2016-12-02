package cn.blinkmind.depot.server.service;

import cn.blinkmind.depot.server.exception.Assertion;
import cn.blinkmind.depot.server.exception.Errors;
import cn.blinkmind.depot.server.repository.BranchRepository;
import cn.blinkmind.depot.server.repository.entity.Archive;
import cn.blinkmind.depot.server.repository.entity.Branch;
import cn.blinkmind.depot.server.repository.entity.Document;
import cn.blinkmind.depot.server.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jiaan.zhang@oracle.com
 * @date 25/11/2016 1:34 PM
 */
@Service
public class BranchService
{
	@Autowired
	private BranchRepository branchRepository;

	@Autowired
	private RepositoryService repositoryService;

	public Branch get(long id, User user)
	{
		return branchRepository.get(id);
	}

	public Branch require(long id, User user)
	{
		Branch branch = branchRepository.get(id);
		Assertion.notNull(branch, Errors.BRANCH_NOT_FOUND);
		return branch;
	}

	public Branch create(Branch branch, Document document, User creator)
	{
		Assertion.isFalse(document == null || document.getId() == null, Errors.BRANCH_DOCUMENT_IS_NOT_SPECIFIED);
		Assertion.notBlank(branch.getName(), Errors.BRANCH_NAME_IS_BLANK);

		branch.setId(repositoryService.newId());
		branch.setCreator(creator);
		branch.setDocument(document);
		branch.setArchive(new Archive());
		branchRepository.insert(branch);
		return branch;
	}

	public void delete(Branch branch, User user)
	{
		branchRepository.delete(branch);
	}
}

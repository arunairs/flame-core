package cn.blinkmind.promise.server.service;

import cn.blinkmind.promise.server.exception.Assertion;
import cn.blinkmind.promise.server.exception.Errors;
import cn.blinkmind.promise.server.repository.BranchRepository;
import cn.blinkmind.promise.server.repository.entity.Branch;
import cn.blinkmind.promise.server.repository.entity.CRUD;
import cn.blinkmind.promise.server.repository.entity.Document;
import cn.blinkmind.promise.server.repository.entity.User;
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

	public Branch create(Branch branch, Document document, User creator)
	{
		Assertion.isFalse(document == null || document.getId() == null, Errors.BRANCH_DOCUMENT_IS_NOT_SPECIFIED);
		Assertion.notBlank(branch.getName(), Errors.BRANCH_NAME_IS_BLANK);

		branch.cleanup(CRUD.CREATE);
		branch.setId(repositoryService.newId());
		branch.setCreator(creator);
		branch.setDocument(document);
		branch.refreshCreatedDate();
		branchRepository.insert(branch);
		return branch;
	}
}

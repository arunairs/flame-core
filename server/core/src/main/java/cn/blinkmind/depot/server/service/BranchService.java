package cn.blinkmind.depot.server.service;

import cn.blinkmind.depot.server.exception.Errors;
import cn.blinkmind.depot.server.repository.ArchiveRepository;
import cn.blinkmind.depot.server.repository.BranchRepository;
import cn.blinkmind.depot.server.repository.entity.*;
import cn.blinkmind.depot.server.exception.Assertion;
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
	private ArchiveRepository archiveRepository;

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

		Archive archive = new Archive();
		archive.setId(repositoryService.newId());
		archive.setCreator(creator);
		archive.refreshCreatedDate();
		branch.setArchive(archive);

		branchRepository.insert(branch);
		return branch;
	}

	public void delete(Branch branch, User user)
	{
		branchRepository.delete(branch);
	}
}

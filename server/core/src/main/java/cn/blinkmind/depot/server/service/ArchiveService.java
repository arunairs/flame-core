package cn.blinkmind.depot.server.service;

import cn.blinkmind.depot.server.bean.patch.JSONPatch;
import cn.blinkmind.depot.server.repository.ArchiveRepository;
import cn.blinkmind.depot.server.repository.BranchRepository;
import cn.blinkmind.depot.server.repository.entity.Archive;
import cn.blinkmind.depot.server.repository.entity.Branch;
import cn.blinkmind.depot.server.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author jiaan.zhang@oracle.com
 * @date 17/10/2016 12:41 AM
 */
@Service
public class ArchiveService
{
	@Autowired
	private ArchiveRepository archiveRepository;

	@Autowired
	private BranchRepository branchRepository;

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private ModuleService moduleService;

	public Archive patch(final Map<String, Object> patch, final Branch branch, final User user)
	{
		final Archive archive = branch.getArchive();
		new JSONPatch<Archive>().source(patch).target(archive)
				.build()
				.update("description")
				.update("request");
		archive.refreshUpdatedDate();
		branchRepository.update(branch);
		return archive;
	}

	public Archive get(final long branchId, final User user)
	{
		Archive archive = archiveRepository.get(branchId);
		return archive;
	}
}

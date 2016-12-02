package cn.blinkmind.depot.server.service;

import cn.blinkmind.depot.server.exception.Assertion;
import cn.blinkmind.depot.server.exception.Errors;
import cn.blinkmind.depot.server.repository.SnapshotRepository;
import cn.blinkmind.depot.server.repository.entity.Archive;
import cn.blinkmind.depot.server.repository.entity.Branch;
import cn.blinkmind.depot.server.repository.entity.Snapshot;
import cn.blinkmind.depot.server.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jiaan.zhang@oracle.com
 * @date 29/11/2016 4:21 PM
 */
@Service
public class SnapshotService
{
	@Autowired
	private SnapshotRepository snapshotRepository;

	@Autowired
	private BranchService branchService;

	@Autowired
	private RepositoryService repositoryService;

	public Snapshot create(Snapshot snapshot, User creator)
	{
		Assertion.isFalse(snapshot.getBranch() == null || snapshot.getBranch().getId() == null, Errors.SNAPSHOT_BRANCH_IS_NOT_SPECIFIED);
		Branch branch = branchService.require(snapshot.getBranch().getId(), creator);

		Assertion.isFalse(snapshotRepository.exists(branch, creator), Errors.RESOURCE_ALREADY_EXISTS);

		snapshot.setId(repositoryService.newId());
		snapshot.setCreator(creator);
		snapshot.setBranch(branch);
		snapshot.setArchive(new Archive());
		snapshotRepository.insert(snapshot);
		return snapshot;
	}

	public Snapshot get(Branch branch, User user)
	{
		Snapshot snapshot = snapshotRepository.get(branch, user);
		return snapshot;
	}

	public Snapshot require(Branch branch, User user)
	{
		Snapshot snapshot = get(branch, user);
		Assertion.notNull(snapshot, Errors.RESOURCE_NOT_FOUND);
		return snapshot;
	}
}

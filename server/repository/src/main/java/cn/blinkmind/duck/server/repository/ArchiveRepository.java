package cn.blinkmind.duck.server.repository;

import cn.blinkmind.duck.server.repository.entity.Archive;
import cn.blinkmind.duck.server.repository.entity.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ArchiveRepository implements cn.blinkmind.duck.server.repository.Repository
{
	@Autowired
	private BranchRepository branchRepository;

	public Archive get(final Branch branch)
	{
		return null;
	}
}

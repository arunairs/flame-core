package cn.blinkmind.depot.server.repository;

import cn.blinkmind.depot.server.repository.entity.Archive;
import cn.blinkmind.depot.server.repository.entity.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ArchiveRepository implements cn.blinkmind.depot.server.repository.Repository
{
	@Autowired
	private BranchRepository branchRepository;

	public Archive get(final Branch branch)
	{
		return null;
	}
}

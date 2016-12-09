package cn.blinkmind.depot.server.repository;

import cn.blinkmind.depot.server.repository.entity.Archive;
import cn.blinkmind.depot.server.repository.entity.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * @author jiaan.zhang@outlook.com
 * @date 28/11/2016 4:05 PM
 */
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

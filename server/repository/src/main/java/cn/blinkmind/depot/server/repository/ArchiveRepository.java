package cn.blinkmind.depot.server.repository;

import cn.blinkmind.depot.server.repository.entity.Archive;
import cn.blinkmind.depot.server.repository.entity.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * @author jiaan.zhang@oracle.com
 * @date 28/11/2016 4:05 PM
 */
@Repository
public class ArchiveRepository implements cn.blinkmind.depot.server.repository.Repository
{
	@Autowired
	private BranchRepository branchRepository;

	public Archive get(long branchId)
	{
		Query query = new Query();
		query.fields().include("archive");
		Branch branch = branchRepository.findOne(query, branchId);
		return branch.getArchive();
	}
}

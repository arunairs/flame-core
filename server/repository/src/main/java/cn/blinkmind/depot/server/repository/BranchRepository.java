package cn.blinkmind.depot.server.repository;

import cn.blinkmind.depot.server.repository.entity.Branch;
import org.springframework.stereotype.Repository;

/**
 * @author jiaan.zhang@oracle.com
 * @date 25/11/2016 1:47 PM
 */
@Repository
public class BranchRepository extends AbstractMongoRepository<Branch, Long>
{
	@Override
	protected Class<Branch> getEntityClass()
	{
		return Branch.class;
	}
}

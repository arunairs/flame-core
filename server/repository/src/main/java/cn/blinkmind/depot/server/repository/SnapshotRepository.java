package cn.blinkmind.depot.server.repository;

import cn.blinkmind.depot.server.repository.entity.Branch;
import cn.blinkmind.depot.server.repository.entity.Snapshot;
import cn.blinkmind.depot.server.repository.entity.User;
import cn.blinkmind.depot.server.repository.exception.ResourceNotFoundException;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * @author jiaan.zhang@oracle.com
 * @date 29/11/2016 4:16 PM
 */
@org.springframework.stereotype.Repository
public class SnapshotRepository extends AbstractMongoRepository<Snapshot, Long>
{
	@Override
	protected Class<Snapshot> getEntityClass()
	{
		return Snapshot.class;
	}

	public Snapshot get(Branch branch, User user)
	{
		Query query = new Query();
		Criteria criteria = Criteria.where("branchRef._id").is(branch.getId())
				.and("creatorRef._id").is(user.getId());
		query.addCriteria(criteria);
		return this.findOne(query);
	}

	public Snapshot require(Branch branch, User user)
	{
		Snapshot snapshot = get(branch, user);
		if (snapshot == null) throw new ResourceNotFoundException();
		return snapshot;
	}

	public boolean exists(Branch branch, User user)
	{
		Query query = new Query();
		Criteria criteria = Criteria.where("branchRef._id").is(branch.getId())
				.and("creatorRef._id").is(user.getId());
		query.addCriteria(criteria);
		return this.exists(query);
	}
}

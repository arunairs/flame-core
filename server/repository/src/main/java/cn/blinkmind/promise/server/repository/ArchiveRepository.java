package cn.blinkmind.promise.server.repository;

import cn.blinkmind.promise.server.repository.entity.Archive;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * @author jiaan.zhang@oracle.com
 * @date 26/09/2016 4:21 PM
 */
@Repository
public class ArchiveRepository extends AbstractMongoRepository<Archive, Long>
{
	@Override
	protected Class<Archive> getEntityClass()
	{
		return Archive.class;
	}

	@Override
	public Archive update(final Archive archive)
	{
		Criteria criteria = Criteria.where("status").ne(Archive.Status.RELEASED);
		Query query = new Query();
		query.addCriteria(criteria);
		return update(query, archive);
	}
}

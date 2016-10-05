package cn.blm.promise.server.repository;

import cn.blm.promise.server.repository.entity.Archive;
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
}

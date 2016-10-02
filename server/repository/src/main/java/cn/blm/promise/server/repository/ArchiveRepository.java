package cn.blm.promise.server.repository;

import cn.blm.promise.server.repository.entity.ArchiveEntity;
import org.springframework.stereotype.Repository;

/**
 * @author jiaan.zhang@oracle.com
 * @date 26/09/2016 4:21 PM
 */
@Repository
public class ArchiveRepository extends AbstractMongoRepository<ArchiveEntity, String>
{
	@Override
	protected Class<ArchiveEntity> getEntityClass()
	{
		return ArchiveEntity.class;
	}
}

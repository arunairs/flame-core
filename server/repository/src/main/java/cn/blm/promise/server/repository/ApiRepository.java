package cn.blm.promise.server.repository;

import cn.blm.promise.server.repository.entity.ApiEntity;
import org.springframework.stereotype.Repository;

/**
 * @author jiaan.zhang@oracle.com
 * @date 26/09/2016 4:21 PM
 */
@Repository
public class ApiRepository extends AbstractMongoRepository<ApiEntity, String>
{
	@Override
	public Class<ApiEntity> getEntityClass()
	{
		return ApiEntity.class;
	}
}

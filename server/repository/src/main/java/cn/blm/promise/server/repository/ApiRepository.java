package cn.blm.promise.server.repository;

import cn.blm.promise.server.repository.entity.Api;
import org.springframework.stereotype.Repository;

/**
 * @author jiaan.zhang@oracle.com
 * @date 26/09/2016 4:21 PM
 */
@Repository
public class ApiRepository extends AbstractMongoRepository<Api, Long>
{
	@Override
	public Class<Api> getEntityClass()
	{
		return Api.class;
	}
}

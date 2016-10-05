package cn.blm.promise.server.repository;

import cn.blm.promise.server.repository.entity.Module;
import org.springframework.stereotype.Repository;

/**
 * @author jiaan.zhang@oracle.com
 * @date 26/09/2016 4:21 PM
 */
@Repository
public class ModuleRepository extends AbstractMongoRepository<Module, Long>
{
	@Override
	protected Class<Module> getEntityClass()
	{
		return Module.class;
	}
}

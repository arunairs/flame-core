package cn.blm.promise.server.repository;

import cn.blm.promise.server.repository.entity.AccountEntity;
import org.springframework.stereotype.Repository;

/**
 * @author jiaan.zhang@oracle.com
 * @date 26/09/2016 4:24 PM
 */
@Repository
public class AccountRepository extends AbstractMongoRepository<AccountEntity, String>
{
	@Override
	protected Class<AccountEntity> getEntityClass()
	{
		return AccountEntity.class;
	}
}

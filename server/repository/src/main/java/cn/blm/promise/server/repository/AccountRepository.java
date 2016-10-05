package cn.blm.promise.server.repository;

import cn.blm.promise.server.repository.entity.Account;
import org.springframework.stereotype.Repository;

/**
 * @author jiaan.zhang@oracle.com
 * @date 26/09/2016 4:24 PM
 */
@Repository
public class AccountRepository extends AbstractMongoRepository<Account, Long>
{
	@Override
	protected Class<Account> getEntityClass()
	{
		return Account.class;
	}
}

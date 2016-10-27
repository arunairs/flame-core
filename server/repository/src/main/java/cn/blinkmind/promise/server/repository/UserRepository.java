package cn.blinkmind.promise.server.repository;

import cn.blinkmind.promise.server.repository.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author jiaan.zhang@oracle.com
 * @date 26/09/2016 4:24 PM
 */
@Repository
public class UserRepository extends AbstractMongoRepository<User, Long>
{
	@Override
	protected Class<User> getEntityClass()
	{
		return User.class;
	}
}

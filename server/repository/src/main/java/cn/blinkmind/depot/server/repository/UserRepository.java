package cn.blinkmind.depot.server.repository;

import cn.blinkmind.depot.server.repository.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author jiaan.zhang@outlook.com
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

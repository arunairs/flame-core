package cn.blinkmind.flame.server.repository;

import cn.blinkmind.flame.server.repository.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends AbstractMongoRepository<User, Long>
{
	@Override
	protected Class<User> getEntityClass()
	{
		return User.class;
	}
}

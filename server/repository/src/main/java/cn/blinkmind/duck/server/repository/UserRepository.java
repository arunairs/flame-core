package cn.blinkmind.duck.server.repository;

import cn.blinkmind.duck.server.repository.entity.User;
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

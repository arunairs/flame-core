package cn.blinkmind.depot.server.repository.entity;

import java.beans.Transient;

@org.springframework.data.mongodb.core.mapping.Document(collection = "users")
public class User extends Account
{
	@Override
	@Transient
	public User getCreator()
	{
		return super.getCreator();
	}
}

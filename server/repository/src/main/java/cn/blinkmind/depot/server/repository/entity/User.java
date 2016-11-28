package cn.blinkmind.depot.server.repository.entity;

import java.beans.Transient;

/**
 * @author jiaan.zhang@oracle.com
 * @date 09/10/2016 4:03 PM
 */
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

package cn.blm.promise.server.repository.domain;

import javax.persistence.Entity;

/**
 * @author jiaan.zhang@oracle.com
 * @date 26/09/2016 4:13 PM
 */
@Entity(name = "account")
public class AccountEntity extends AbstractEntity
{
	private String username;
	private String password;

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
}

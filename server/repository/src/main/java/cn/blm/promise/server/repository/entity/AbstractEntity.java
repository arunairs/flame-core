package cn.blm.promise.server.repository.entity;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * @author jiaan.zhang@oracle.com
 * @date 26/09/2016 2:44 PM
 */
public abstract class AbstractEntity
{
	private String id;
	private Date createdDate = new Date();
	private Date updatedDate;

	@Id
	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public Date getCreatedDate()
	{
		return createdDate;
	}

	public void setCreatedDate(Date createdDate)
	{
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate()
	{
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate)
	{
		this.updatedDate = updatedDate;
	}
}

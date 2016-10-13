package cn.blm.promise.server.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.util.Date;

/**
 * @author jiaan.zhang@oracle.com
 * @date 26/09/2016 2:44 PM
 */
abstract class BaseEntity
{
	private Long id;
	private Long creatorId;
	private Date createdDate;
	private Date updatedDate;
	private User creator;

	@Id
	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	@JsonIgnore
	public Long getCreatorId()
	{
		return creatorId;
	}

	private void setCreatorId(Long creatorId)
	{
		this.creatorId = creatorId;
	}

	public Date getCreatedDate()
	{
		return createdDate;
	}

	public void setCreatedDate(Date createdDate)
	{
		this.createdDate = createdDate;
	}

	public void writeCreatedDate()
	{
		setCreatedDate(new Date());
	}

	public Date getUpdatedDate()
	{
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate)
	{
		this.updatedDate = updatedDate;
	}

	@Transient
	@JsonIgnoreProperties({"id", "createdDate", "updatedDate"})
	public User getCreator()
	{
		return creator;
	}

	public void setCreator(User creator)
	{
		this.creator = creator;
		if (this.creator != null)
			setCreatorId(this.creator.getId());
	}
}

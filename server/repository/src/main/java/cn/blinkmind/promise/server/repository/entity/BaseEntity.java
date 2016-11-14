package cn.blinkmind.promise.server.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.util.Date;

/**
 * @author jiaan.zhang@oracle.com
 * @date 26/09/2016 2:44 PM
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
abstract class BaseEntity implements Indexable<Long>, Cleanable
{
	private Long id;
	private Ref<Long> creatorRef;
	private Date createdDate;
	private Date updatedDate;
	private User creator;

	@Id
	@Override
	public Long getId()
	{
		return id;
	}

	@Override
	public void setId(Long id)
	{
		this.id = id;
	}

	@JsonIgnore
	public Ref<Long> getCreatorRef()
	{
		return creatorRef;
	}

	private void setCreatorRef(Ref<Long> creatorRef)
	{
		this.creatorRef = creatorRef;
	}

	public Date getCreatedDate()
	{
		return createdDate;
	}

	public void setCreatedDate(Date createdDate)
	{
		this.createdDate = createdDate;
	}

	public void refreshCreatedDate()
	{
		this.createdDate = new Date();
	}

	public Date getUpdatedDate()
	{
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate)
	{
		this.updatedDate = updatedDate;
	}

	public void refreshUpdatedDate()
	{
		setUpdatedDate(new Date());
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
			setCreatorRef(new Ref<>(this.creator));
		else setCreatorRef(null);
	}

	@Override
	public void clean()
	{
		this.id = null;
		this.creatorRef = null;
		this.createdDate = null;
		this.updatedDate = null;
		this.creator = null;
	}
}

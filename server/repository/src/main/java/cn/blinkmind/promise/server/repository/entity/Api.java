package cn.blinkmind.promise.server.repository.entity;

import cn.blinkmind.promise.server.bean.web.Request;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author jiaan.zhang@oracle.com
 * @date 26/09/2016 4:13 PM
 */
@Document(collection = "apis")
public class Api extends BaseEntity
{
	private String name;
	private String description;
	private boolean isFrozen = false;
	private Request request;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	@JsonIgnore
	public boolean isFrozen()
	{
		return isFrozen;
	}

	public void setFrozen(boolean frozen)
	{
		isFrozen = frozen;
	}

	public Request getRequest()
	{
		return request;
	}

	public void setRequest(Request request)
	{
		this.request = request;
	}
}

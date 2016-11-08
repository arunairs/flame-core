package cn.blinkmind.promise.server.repository.entity;

import cn.blinkmind.promise.server.bean.web.Request;
import cn.blinkmind.promise.server.repository.util.UrlStringBuilder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author jiaan.zhang@oracle.com
 * @date 26/09/2016 4:13 PM
 */
@Document(collection = "apis")
public class Api extends BaseEntity implements Locatable
{
	private String name;
	private String description;
	private boolean isFrozen = false;
	private Request request;
	private Module module;

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

	@Transient
	@JsonIgnore
	public Module getModule()
	{
		return module;
	}

	public void setModule(Module module)
	{
		this.module = module;
	}

	@Override
	public void clean()
	{
		super.clean();
		this.isFrozen = false;
	}

	@Override
	public String getScheme()
	{
		if (this.request != null && StringUtils.isNotBlank(this.request.getScheme()))
		{
			return this.request.getScheme();
		}
		if (this.module != null && StringUtils.isNotBlank(this.module.getScheme()))
		{
			return this.module.getScheme();
		}
		return null;
	}

	@Override
	public String getUri()
	{
		UrlStringBuilder stringBuilder = new UrlStringBuilder();
		if (this.module != null)
		{
			stringBuilder.append(this.module.getUri());
		}
		if (this.request != null && StringUtils.isNotBlank(this.request.getUri()))
		{
			stringBuilder.append(this.request.getUri());
		}
		return stringBuilder.toString();
	}
}

package cn.blinkmind.promise.server.repository.entity;

import cn.blinkmind.promise.server.bean.web.Request;
import cn.blinkmind.promise.server.repository.util.UrlStringBuilder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

/**
 * @author jiaan.zhang@oracle.com
 * @date 26/09/2016 3:57 PM
 */
@Document(collection = "modules")
public class Module extends BaseEntity implements Locatable
{
	private String name;
	private Request request;
	private boolean isFrozen = false;
	private ArrayList<Api> apis;
	private Archive archive;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Request getRequest()
	{
		return request;
	}

	public void setRequest(Request request)
	{
		this.request = request;
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

	@Transient
	public ArrayList<Api> getApis()
	{
		return apis;
	}

	public void setApis(ArrayList<Api> apis)
	{
		this.apis = apis;
	}

	@Transient
	@JsonIgnore
	public Archive getArchive()
	{
		return archive;
	}

	public void setArchive(Archive archive)
	{
		this.archive = archive;
	}

	@Override
	public void clean()
	{
		super.clean();
		this.isFrozen = false;
		if (this.request != null)
		{
			Request request = new Request();
			request.setUri(this.request.getUri());
			request.setScheme(this.request.getScheme());
			this.request = request;
		}
	}

	@Override
	public String getScheme()
	{
		if (this.request != null && StringUtils.isNotBlank(this.request.getScheme()))
		{
			return this.request.getScheme();
		}
		if (this.archive != null && StringUtils.isNotBlank(this.archive.getScheme()))
		{
			return this.archive.getScheme();
		}
		return null;
	}

	@Override
	public String getUri()
	{
		UrlStringBuilder stringBuilder = new UrlStringBuilder();
		if (this.archive != null)
		{
			stringBuilder.append(this.archive.getUri());
		}
		if (this.request != null && StringUtils.isNotBlank(this.request.getUri()))
		{
			stringBuilder.append(this.request.getUri());
		}
		return stringBuilder.toString();
	}
}

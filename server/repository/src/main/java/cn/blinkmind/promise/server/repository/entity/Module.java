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
public class Module extends BaseEntity implements Resource
{
	private String name;
	private Request request;
	private boolean isUpdatable = true;
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
	@Override
	public boolean isUpdatable()
	{
		return isUpdatable;
	}

	public void setUpdatable(boolean updatable)
	{
		isUpdatable = updatable;
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

	public void addApi(Api api)
	{
		if (apis == null) apis = new ArrayList<>();
		apis.add(api);
		api.setModule(this);
	}

	@Override
	public void clean()
	{
		super.clean();
		this.isUpdatable = true;
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
		if (getParent() != null && StringUtils.isNotBlank(getParent().getScheme()))
		{
			return getParent().getScheme();
		}
		return null;
	}

	@Override
	public String getUri()
	{
		UrlStringBuilder stringBuilder = new UrlStringBuilder();
		if (getParent() != null)
		{
			stringBuilder.append(getParent().getUri());
		}
		if (this.request != null && StringUtils.isNotBlank(this.request.getUri()))
		{
			stringBuilder.append(this.request.getUri());
		}
		return stringBuilder.toString();
	}

	@JsonIgnore
	@Override
	public Resource getParent()
	{
		return archive;
	}
}

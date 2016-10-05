package cn.blm.promise.server.repository.entity;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author jiaan.zhang@oracle.com
 * @date 26/09/2016 4:13 PM
 */
@Document(collection = "apis")
public class Api extends BaseEntity
{
	private String name;
	private String url;
	private String description;
	private String requestMethods;
	private String pathParam;
	private String queryParam;
	private String headerParam;
	private String cookieParam;
	private String binaryParam;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getRequestMethods()
	{
		return requestMethods;
	}

	public void setRequestMethods(String requestMethods)
	{
		this.requestMethods = requestMethods;
	}

	public String getPathParam()
	{
		return pathParam;
	}

	public void setPathParam(String pathParam)
	{
		this.pathParam = pathParam;
	}

	public String getQueryParam()
	{
		return queryParam;
	}

	public void setQueryParam(String queryParam)
	{
		this.queryParam = queryParam;
	}

	public String getHeaderParam()
	{
		return headerParam;
	}

	public void setHeaderParam(String headerParam)
	{
		this.headerParam = headerParam;
	}

	public String getCookieParam()
	{
		return cookieParam;
	}

	public void setCookieParam(String cookieParam)
	{
		this.cookieParam = cookieParam;
	}

	public String getBinaryParam()
	{
		return binaryParam;
	}

	public void setBinaryParam(String binaryParam)
	{
		this.binaryParam = binaryParam;
	}
}

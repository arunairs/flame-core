package cn.blinkmind.server.repository.entity;

/**
 * @author jiaan.zhang@oracle.com
 * @date 26/09/2016 4:13 PM
 */
public class ApiEntity extends AbstractEntity
{
	private String name;
	private String url;
	private String description;
	private String requestMethods;
	private String pathParamJson;
	private String queryParamJson;
	private String headerParamJson;
	private String cookieParamJson;
	private String binaryParamJson;

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

	public String getPathParamJson()
	{
		return pathParamJson;
	}

	public void setPathParamJson(String pathParamJson)
	{
		this.pathParamJson = pathParamJson;
	}

	public String getQueryParamJson()
	{
		return queryParamJson;
	}

	public void setQueryParamJson(String queryParamJson)
	{
		this.queryParamJson = queryParamJson;
	}

	public String getHeaderParamJson()
	{
		return headerParamJson;
	}

	public void setHeaderParamJson(String headerParamJson)
	{
		this.headerParamJson = headerParamJson;
	}

	public String getCookieParamJson()
	{
		return cookieParamJson;
	}

	public void setCookieParamJson(String cookieParamJson)
	{
		this.cookieParamJson = cookieParamJson;
	}

	public String getBinaryParamJson()
	{
		return binaryParamJson;
	}

	public void setBinaryParamJson(String binaryParamJson)
	{
		this.binaryParamJson = binaryParamJson;
	}
}

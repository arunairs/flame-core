package cn.blinkmind.promise.server.bean.web;

import java.util.*;

/**
 * @author jiaan.zhang@oracle.com
 * @date 11/10/2016 12:07 AM
 */
public class Request
{
	private String url;
	private Set<RequestMethod> requestMethods = new LinkedHashSet<>();
	private Set<RequestParameter> headers = new LinkedHashSet<>();
	private Set<RequestParameter> paths = new LinkedHashSet<>();
	private Set<RequestParameter> queries = new LinkedHashSet<>();
	private Set<RequestParameter> cookies = new LinkedHashSet<>();
	private RequestParameter body;

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public Set<RequestMethod> getRequestMethods()
	{
		return requestMethods;
	}

	public Set<RequestParameter> getHeaders()
	{
		return headers;
	}

	public Set<RequestParameter> getPaths()
	{
		return paths;
	}

	public Set<RequestParameter> getQueries()
	{
		return queries;
	}

	public Set<RequestParameter> getCookies()
	{
		return cookies;
	}

	public RequestParameter getBody()
	{
		return body;
	}

	public void setBody(RequestParameter body)
	{
		this.body = body;
	}
}

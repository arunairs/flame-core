package cn.blinkmind.promise.server.bean.web;

import java.util.*;

/**
 * @author jiaan.zhang@oracle.com
 * @date 11/10/2016 12:07 AM
 */
public class Request
{
	private String url;
	private Set<RequestMethod> methods = new LinkedHashSet<>();
	private Set<SingleFieldRequestParameter> headers = new LinkedHashSet<>();
	private Set<SingleFieldRequestParameter> paths = new LinkedHashSet<>();
	private Set<SingleFieldRequestParameter> queries = new LinkedHashSet<>();
	private Set<SingleFieldRequestParameter> cookies = new LinkedHashSet<>();
	private Body body;

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public Set<RequestMethod> getMethods()
	{
		return methods;
	}

	public Set<SingleFieldRequestParameter> getHeaders()
	{
		return headers;
	}

	public Set<SingleFieldRequestParameter> getPaths()
	{
		return paths;
	}

	public Set<SingleFieldRequestParameter> getQueries()
	{
		return queries;
	}

	public Set<SingleFieldRequestParameter> getCookies()
	{
		return cookies;
	}

	public Body getBody()
	{
		return body;
	}

	public void setBody(Body body)
	{
		this.body = body;
	}

	public static class Body
	{
		private Set<SingleFieldRequestParameter> parameters = new LinkedHashSet<>();
		private String text;

		public Set<SingleFieldRequestParameter> getParameters()
		{
			return parameters;
		}

		public void setParameters(Set<SingleFieldRequestParameter> parameters)
		{
			this.text = null;
			this.parameters = parameters;
		}

		public String getText()
		{
			return text;
		}

		public void setText(String text)
		{
			this.parameters.clear();
			this.text = text;
		}
	}
}

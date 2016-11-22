package cn.blinkmind.promise.server.bean.web;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.*;

/**
 * @author jiaan.zhang@oracle.com
 * @date 11/10/2016 12:07 AM
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Request
{
	private String uri;
	private String scheme;
	private LinkedHashSet<RequestMethod> methods;
	private LinkedHashSet<SingleFieldRequestParameter> headers;
	private LinkedHashSet<SingleFieldRequestParameter> paths;
	private LinkedHashSet<SingleFieldRequestParameter> queries;
	private LinkedHashSet<SingleFieldRequestParameter> cookies;
	private Body body;

	public String getUri()
	{
		return uri;
	}

	public void setUri(String uri)
	{
		this.uri = uri;
	}

	public String getScheme()
	{
		return scheme;
	}

	public void setScheme(String scheme)
	{
		this.scheme = scheme;
	}

	public LinkedHashSet<RequestMethod> getMethods()
	{
		return methods;
	}

	public void setMethods(LinkedHashSet<RequestMethod> methods)
	{
		this.methods = methods;
	}

	public LinkedHashSet<SingleFieldRequestParameter> getHeaders()
	{
		return headers;
	}

	public void setHeaders(LinkedHashSet<SingleFieldRequestParameter> headers)
	{
		this.headers = headers;
	}

	public LinkedHashSet<SingleFieldRequestParameter> getPaths()
	{
		return paths;
	}

	public void setPaths(LinkedHashSet<SingleFieldRequestParameter> paths)
	{
		this.paths = paths;
	}

	public LinkedHashSet<SingleFieldRequestParameter> getQueries()
	{
		return queries;
	}

	public void setQueries(LinkedHashSet<SingleFieldRequestParameter> queries)
	{
		this.queries = queries;
	}

	public LinkedHashSet<SingleFieldRequestParameter> getCookies()
	{
		return cookies;
	}

	public void setCookies(LinkedHashSet<SingleFieldRequestParameter> cookies)
	{
		this.cookies = cookies;
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
		private LinkedHashSet<SingleFieldRequestParameter> parameters;
		private String text;

		public LinkedHashSet<SingleFieldRequestParameter> getParameters()
		{
			return parameters;
		}

		public void setParameters(LinkedHashSet<SingleFieldRequestParameter> parameters)
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

	public static Request getBriefCopy(Request request)
	{
		Request copy = new Request();
		copy.setUri(request.getUri());
		copy.setScheme(request.getScheme());
		return copy;
	}
}

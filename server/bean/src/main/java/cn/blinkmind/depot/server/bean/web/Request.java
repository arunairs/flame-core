package cn.blinkmind.depot.server.bean.web;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.*;

/**
 * @author jiaan.zhang@outlook.com
 * @date 11/10/2016 12:07 AM
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Request extends GeneralRequest
{
	private LinkedHashSet<SingleFieldRequestParameter> headers;
	private LinkedHashSet<SingleFieldRequestParameter> paths;
	private LinkedHashSet<SingleFieldRequestParameter> queries;
	private LinkedHashSet<SingleFieldRequestParameter> cookies;
	private Body body;

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

	public static GeneralRequest getGeneralCopy(Request request)
	{
		GeneralRequest copy = new GeneralRequest();
		copy.setUri(request.getUri());
		copy.setScheme(request.getScheme());
		return copy;
	}
}

package cn.blinkmind.flame.server.bean.web.http;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.*;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class HttpRequest extends BasicHttpRequest
{
	private LinkedHashSet<SingleFieldHttpRequestParameter> headers;
	private LinkedHashSet<SingleFieldHttpRequestParameter> paths;
	private LinkedHashSet<SingleFieldHttpRequestParameter> queries;
	private LinkedHashSet<SingleFieldHttpRequestParameter> cookies;
	private HttpRequestBody body;

	public LinkedHashSet<SingleFieldHttpRequestParameter> getHeaders()
	{
		return headers;
	}

	public void setHeaders(LinkedHashSet<SingleFieldHttpRequestParameter> headers)
	{
		this.headers = headers;
	}

	public LinkedHashSet<SingleFieldHttpRequestParameter> getPaths()
	{
		return paths;
	}

	public void setPaths(LinkedHashSet<SingleFieldHttpRequestParameter> paths)
	{
		this.paths = paths;
	}

	public LinkedHashSet<SingleFieldHttpRequestParameter> getQueries()
	{
		return queries;
	}

	public void setQueries(LinkedHashSet<SingleFieldHttpRequestParameter> queries)
	{
		this.queries = queries;
	}

	public LinkedHashSet<SingleFieldHttpRequestParameter> getCookies()
	{
		return cookies;
	}

	public void setCookies(LinkedHashSet<SingleFieldHttpRequestParameter> cookies)
	{
		this.cookies = cookies;
	}

	public HttpRequestBody getBody()
	{
		return body;
	}

	public void setBody(HttpRequestBody body)
	{
		this.body = body;
	}
}

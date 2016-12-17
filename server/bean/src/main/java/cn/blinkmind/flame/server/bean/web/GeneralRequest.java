package cn.blinkmind.flame.server.bean.web;

import java.util.LinkedHashSet;

public class GeneralRequest
{
	private String uri;
	private String scheme;
	private LinkedHashSet<RequestMethod> methods;

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
}

package cn.blinkmind.promise.server.bean.web;

import java.util.LinkedHashSet;

/**
 * @author jiaan.zhang@oracle.com
 * @date 23/11/2016 5:47 PM
 */
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

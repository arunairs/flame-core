package cn.blinkmind.promise.server.bean.web;

import java.util.Set;

/**
 * @author jiaan.zhang@oracle.com
 * @date 14/10/2016 3:50 PM
 */
public class TextRequestParameter implements DataRequestParameter<String>, ParameterComment<Set<RequestParameter>>
{
	private String data;
	private Set<RequestParameter> comment;

	@Override
	public void setData(String data)
	{
		this.data = data;
	}

	@Override
	public String getData()
	{
		return data;
	}

	@Override
	public void setComment(Set<RequestParameter> comment)
	{
		this.comment = comment;
	}

	@Override
	public Set<RequestParameter> getComment()
	{
		return comment;
	}
}

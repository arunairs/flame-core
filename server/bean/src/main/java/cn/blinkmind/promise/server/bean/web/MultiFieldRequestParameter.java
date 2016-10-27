package cn.blinkmind.promise.server.bean.web;

import java.util.Set;

/**
 * @author jiaan.zhang@oracle.com
 * @date 14/10/2016 5:10 PM
 */
public class MultiFieldRequestParameter implements DataRequestParameter<Set<SingleFieldRequestParameter>>
{
	private Set<SingleFieldRequestParameter> data;

	@Override
	public void setData(Set<SingleFieldRequestParameter> data)
	{
		this.data = data;
	}

	@Override
	public Set<SingleFieldRequestParameter> getData()
	{
		return data;
	}
}

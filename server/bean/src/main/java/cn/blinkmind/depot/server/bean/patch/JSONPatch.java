package cn.blinkmind.depot.server.bean.patch;

import cn.blinkmind.depot.server.util.JSONUtil;

import java.util.Map;

public class JSONPatch<T> extends Patch<Map, T>
{
	@Override
	protected T convert(Map source)
	{
		return JSONUtil.convert(source, getTargetClass());
	}

	@Override
	protected boolean contains(Map source, String fieldName)
	{
		return source.containsKey(fieldName);
	}

	public static JSONPatch newInstance()
	{
		return new JSONPatch<>();
	}
}

package cn.blinkmind.flame.server.bean.patch;

import cn.blinkmind.flame.server.util.JSONUtil;

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

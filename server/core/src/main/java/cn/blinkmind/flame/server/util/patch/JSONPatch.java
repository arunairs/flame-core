package cn.blinkmind.flame.server.util.patch;

import cn.blinkmind.flame.server.util.JSONUtil;

import java.util.Map;

public class JSONPatch<T> extends Patch<T, Map>
{
    @Override
    protected T convert(final Map source)
    {
        return JSONUtil.convert(source, getTargetClass());
    }

    @Override
    protected boolean contains(final Map source, final String fieldName)
    {
        return source.containsKey(fieldName);
    }

    public static <T> JSONPatch<T> on(final T target)
    {
        return (JSONPatch<T>) new JSONPatch<T>().bind(target);
    }
}

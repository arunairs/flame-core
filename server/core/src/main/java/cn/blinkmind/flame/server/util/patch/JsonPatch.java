package cn.blinkmind.flame.server.util.patch;

import cn.blinkmind.flame.server.util.JsonUtil;

import java.util.Map;

public class JsonPatch<T> extends Patch<T, Map>
{
    @Override
    protected T convert(final Map source)
    {
        return JsonUtil.convert(source, getTargetClass());
    }

    @Override
    protected boolean contains(final Map source, final String fieldName)
    {
        return source.containsKey(fieldName);
    }

    public static <T> JsonPatch<T> on(final T target)
    {
        return (JsonPatch<T>) new JsonPatch<T>().bind(target);
    }
}

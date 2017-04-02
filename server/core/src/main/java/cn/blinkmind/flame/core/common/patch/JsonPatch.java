package cn.blinkmind.flame.core.common.patch;

import io.bayberry.common.util.JsonUtils;

import java.util.Map;

public class JsonPatch<T> extends Patch<T, Map>
{
    @Override
    protected T convert(final Map source)
    {
        return JsonUtils.convert(source, getTargetClass());
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

package cn.blinkmind.promise.server.bean.patch;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author jiaan.zhang@oracle.com
 * @date 15/11/2016 11:11 PM
 */
public abstract class Patch<S, T>
{
	private static final String METHOD_PREFIX_GET = "get";
	private static final String METHOD_PREFIX_IS = "is";
	private static final String METHOD_PREFIX_SET = "set";

	private Class<T> targetClass;
	private T target;
	private T converted;
	private S source;

	protected Class<T> getTargetClass()
	{
		return targetClass;
	}

	protected S getSource()
	{
		return source;
	}

	protected T getTarget()
	{
		return target;
	}

	public Patch<S, T> source(final S source)
	{
		this.source = source;
		return this;
	}

	public Patch<S, T> target(final T target)
	{
		this.target = target;
		this.targetClass = (Class<T>) this.target.getClass();
		return this;
	}

	protected T getConverted()
	{
		if (this.converted == null)
			this.converted = convert(source);
		return converted;
	}

	public Patch<S, T> update(final String sourceFieldName, final String targetFieldName, final PatchCallback<?, T> callback)
	{
		update(getSource(), sourceFieldName, getConverted(), getTarget(), targetFieldName, callback);
		return this;
	}

	public Patch<S, T> update(final String sourceFieldName, final String targetFieldName)
	{
		return update(sourceFieldName, targetFieldName, null);
	}

	public Patch<S, T> update(final String fieldName, final PatchCallback<?, T> callback)
	{
		return update(fieldName, fieldName, callback);
	}

	public Patch<S, T> update(final String fieldName)
	{
		return update(fieldName, fieldName);
	}

	protected abstract T convert(S source);

	protected abstract boolean contains(final S source, final String fieldName);

	protected <V> boolean update(final S source, final String sourceFieldName, final T converted, final T target, final String targetFieldName, final PatchCallback<V, T> callback)
	{
		if (source == null || !contains(source, sourceFieldName)) return false;
		try
		{
			Class<T> targetClass = getTargetClass();
			Field field = targetClass.getDeclaredField(targetFieldName);
			field.setAccessible(true);
			Class<?> targetFieldClass = field.getType();
			String fieldName = StringUtils.capitalize(targetFieldName);
			Method setterMethod = targetClass.getDeclaredMethod(METHOD_PREFIX_SET + fieldName, targetFieldClass);
			setterMethod.setAccessible(true);

			Method getterMethod;
			if (boolean.class.equals(targetFieldClass) || Boolean.class.equals(targetFieldClass))
				getterMethod = targetClass.getDeclaredMethod(METHOD_PREFIX_IS + fieldName);
			else
				getterMethod = targetClass.getDeclaredMethod(METHOD_PREFIX_GET + fieldName);
			getterMethod.setAccessible(true);
			V previous = (V) getterMethod.invoke(target);
			V current = (V) getterMethod.invoke(converted);
			setterMethod.invoke(target, current);
			if (callback != null)
				callback.updated(previous, current, getConverted());
		}
		catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | NoSuchFieldException e)
		{
			throw new RuntimeException(e);
		}
		return true;
	}
}

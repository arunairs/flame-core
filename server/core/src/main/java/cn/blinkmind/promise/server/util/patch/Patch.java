package cn.blinkmind.promise.server.util.patch;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Constructor;
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

	public Patch<S, T> update(final String sourceFieldName, final Class<?> sourceFieldClass, final String targetFieldName, final Class<?> targetFieldClass)
	{
		update(getSource(), sourceFieldName, sourceFieldClass, getConverted(), getTarget(), targetFieldName, targetFieldClass);
		return this;
	}

	public Patch<S, T> update(final String sourceFieldName, final String targetFieldName, final Class<?> fieldClass)
	{
		return update(sourceFieldName, fieldClass, targetFieldName, fieldClass);
	}

	protected abstract T convert(S source);

	protected abstract boolean contains(final S source, final String fieldName, final Class<?> fieldClass);

	protected <U, V> boolean update(final S source, final String sourceFieldName, final Class<U> sourceFieldClass, final T converted, final T target, final String targetFieldName, final Class<V> targetFieldClass)
	{
		if (source == null || !contains(source, sourceFieldName, sourceFieldClass)) return false;
		try
		{
			Class<T> targetClass = (Class<T>) target.getClass();
			String targetProperty = StringUtils.capitalize(targetFieldName);
			Method setterMethod = targetClass.getDeclaredMethod(METHOD_PREFIX_SET + targetProperty, targetFieldClass);
			setterMethod.setAccessible(true);

			Method getterMethod;
			if (boolean.class.equals(targetFieldClass) || Boolean.class.equals(targetFieldClass))
				getterMethod = targetClass.getDeclaredMethod(METHOD_PREFIX_IS + targetProperty);
			else
				getterMethod = targetClass.getDeclaredMethod(METHOD_PREFIX_GET + targetProperty);
			getterMethod.setAccessible(true);
			V value = (V) getterMethod.invoke(converted);

			setterMethod.invoke(target, value);
		}
		catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e)
		{
			throw new RuntimeException(e);
		}
		return true;
	}

	public static Patch create(final Class<? extends Patch> patchClass)
	{
		try
		{
			Constructor<? extends Patch> constructor = patchClass.getDeclaredConstructor();
			constructor.setAccessible(true);
			return constructor.newInstance();
		}
		catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e)
		{
			throw new RuntimeException(e);
		}
	}
}

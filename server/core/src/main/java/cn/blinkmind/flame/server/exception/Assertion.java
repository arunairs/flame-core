package cn.blinkmind.flame.server.exception;

import org.apache.commons.lang3.StringUtils;

import java.lang.*;

public class Assertion
{
	public static void notNull(Object object, Error error)
	{
		if (object == null) doAssert(error);
	}

	public static void isNull(Object object, Error error)
	{
		if (object != null) doAssert(error);
	}

	public static void isTrue(boolean condition, Error error)
	{
		if (!condition) doAssert(error);
	}

	public static void isFalse(boolean condition, Error error)
	{
		if (condition) doAssert(error);
	}

	public static void notBlank(String string, Error error)
	{
		if (StringUtils.isBlank(string)) doAssert(error);
	}

	public static void isBlank(String string, Error error)
	{
		if (!StringUtils.isBlank(string)) doAssert(error);
	}

	private static void doAssert(Error error)
	{
		Error.occurs(error);
	}
}

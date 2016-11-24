package cn.blinkmind.promise.server.exception;

/**
 * @author jiaan.zhang@oracle.com
 * @date 25/11/2016 12:59 AM
 */
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

	private static void doAssert(Error error)
	{
		Error.occurs(error);
	}
}

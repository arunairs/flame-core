package cn.blinkmind.flame.server.util;

import org.apache.commons.lang3.StringUtils;

public class Assert
{
    public static void notNull(Object object, RuntimeException exception)
    {
        if (object == null) doAssert(exception);
    }

    public static void isNull(Object object, RuntimeException exception)
    {
        if (object != null) doAssert(exception);
    }

    public static void isTrue(boolean condition, RuntimeException exception)
    {
        if (!condition) doAssert(exception);
    }

    public static void isFalse(boolean condition, RuntimeException exception)
    {
        if (condition) doAssert(exception);
    }

    public static void notBlank(String string, RuntimeException exception)
    {
        if (StringUtils.isBlank(string)) doAssert(exception);
    }

    public static void isBlank(String string, RuntimeException exception)
    {
        if (!StringUtils.isBlank(string)) doAssert(exception);
    }

    private static void doAssert(RuntimeException exception)
    {
        throw exception;
    }
}

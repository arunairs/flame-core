package cn.blinkmind.flame.server.util;

import org.apache.commons.lang3.StringUtils;

public class Assert
{
    public static void notNull(Object object, RuntimeException exception)
    {
        if (object == null) onAssertFailure(exception);
    }

    public static void isNull(Object object, RuntimeException exception)
    {
        if (object != null) onAssertFailure(exception);
    }

    public static void isTrue(boolean condition, RuntimeException exception)
    {
        if (!condition) onAssertFailure(exception);
    }

    public static void isFalse(boolean condition, RuntimeException exception)
    {
        if (condition) onAssertFailure(exception);
    }

    public static void notBlank(String string, RuntimeException exception)
    {
        if (StringUtils.isBlank(string)) onAssertFailure(exception);
    }

    public static void isBlank(String string, RuntimeException exception)
    {
        if (!StringUtils.isBlank(string)) onAssertFailure(exception);
    }

    public static <T> void notEquals(T value1, T value2, RuntimeException exception)
    {
        if (value1 != null && value2 != null && value1.equals(value2)) onAssertFailure(exception);
    }

    public static <T> void equals(T value1, T value2, RuntimeException exception)
    {
        if (value1 == null || value2 == null || !value1.equals(value2)) onAssertFailure(exception);
    }

    private static void onAssertFailure(RuntimeException exception)
    {
        throw exception;
    }
}

package cn.blinkmind.flame.common.util;

import java.lang.reflect.InvocationTargetException;

public class BeanUtils {
    public static void copyProperties(final Object dest, final Object orig) {
        if (dest == null || orig == null) return;
        try {
            org.apache.commons.beanutils.BeanUtils.copyProperties(dest, orig);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}

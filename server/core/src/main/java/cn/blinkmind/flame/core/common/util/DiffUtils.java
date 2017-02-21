package cn.blinkmind.flame.core.common.util;

import cn.blinkmind.flame.core.bean.DiffResult;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.reflect.FieldUtils;

public class DiffUtils {

    public static <T> DiffResult diff(String fieldName, T base, T head) {
        try {
            return EqualsBuilder.reflectionEquals(FieldUtils.readField(base, fieldName, true), FieldUtils.readField(head, fieldName, true)) ? DiffResult.IDENTICAL : DiffResult.MODIFIED;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}

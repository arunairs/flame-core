package io.bayberry.core.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.reflect.FieldUtils;

public class DiffResolver {

    public static <T> DiffType diff(String fieldName, T base, T head) {
        try {
            return EqualsBuilder.reflectionEquals(FieldUtils.readField(base, fieldName, true), FieldUtils.readField(head, fieldName, true)) ? DiffType.IDENTICAL : DiffType.MODIFIED;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}

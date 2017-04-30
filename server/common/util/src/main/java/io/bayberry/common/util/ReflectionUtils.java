package io.bayberry.common.util;

import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.Set;

public class ReflectionUtils {

    public static Set<Class<?>> getClassesAnnotatedWith(final String packageName, final Annotation annotation) {
        return getReflections(packageName).getTypesAnnotatedWith(annotation);
    }

    private static Reflections getReflections(final String packageName) {
        return new Reflections(packageName);
    }
}

package io.bayberry.common.util;

import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.Set;

public class ReflectionUtils {

    public static Set<Class<?>> getClassesAnnotatedWith(final Class<?> basePackageClass,
                                                        final Class<? extends Annotation> annotationClass) {
        return getClassesAnnotatedWith(basePackageClass.getPackage().getName(), annotationClass);
    }

    public static Set<Class<?>> getClassesAnnotatedWith(final String packageName,
                                                        final Class<? extends Annotation> annotationClass) {
        return getReflections(packageName).getTypesAnnotatedWith(annotationClass);
    }

    private static Reflections getReflections(final String packageName) {
        return new Reflections(packageName);
    }
}

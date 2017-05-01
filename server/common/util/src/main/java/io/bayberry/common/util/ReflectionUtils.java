package io.bayberry.common.util;

import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Set;

public class ReflectionUtils {

    public static <T> Type[] getParameterizedTypesOf(final Class<T> targetClass,
                                                     final Class<? super T> stopClass) {
        return recursiveFindParameterizedTypesOf(targetClass, stopClass);
    }

    @SuppressWarnings("unchecked")
    private static <T> Type[] recursiveFindParameterizedTypesOf(final Class<? super T> targetClass,
                                                                final Class<? super T> stopClass) {
        if (stopClass == targetClass.getSuperclass()) {
            ParameterizedType parameterizedType = (ParameterizedType) targetClass.getGenericSuperclass();
            return parameterizedType.getActualTypeArguments();
        }
        return recursiveFindParameterizedTypesOf(targetClass.getSuperclass(), stopClass);
    }

    public static <T> Set<Class<? extends T>> getSubTypesOf(final Class<?> basePackageClass, final Class<T> baseClass) {
        return getSubTypesOf(basePackageClass.getPackage().getName(), baseClass);
    }

    public static <T> Set<Class<? extends T>> getSubTypesOf(final String packageName, final Class<T> baseClass) {
        return getReflections(packageName).getSubTypesOf(baseClass);
    }

    public static Set<Class<?>> getTypesAnnotatedWith(final Class<?> basePackageClass,
                                                      final Class<? extends Annotation> annotationClass) {
        return getTypesAnnotatedWith(basePackageClass.getPackage().getName(), annotationClass);
    }

    public static Set<Class<?>> getTypesAnnotatedWith(final String packageName,
                                                      final Class<? extends Annotation> annotationClass) {
        return getReflections(packageName).getTypesAnnotatedWith(annotationClass);
    }

    private static Reflections getReflections(final String packageName) {
        return new Reflections(packageName);
    }
}

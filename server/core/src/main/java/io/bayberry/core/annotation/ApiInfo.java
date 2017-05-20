package io.bayberry.core.annotation;

import io.bayberry.core.repository.entity.ApiType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ApiInfo {

    ApiType type();
}

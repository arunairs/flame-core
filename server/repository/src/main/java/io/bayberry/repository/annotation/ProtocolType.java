package io.bayberry.repository.annotation;

import io.bayberry.common.protocol.Protocol;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ProtocolType {

    Protocol value();
}

package io.bayberry.core.controller.advice;

import io.bayberry.common.util.ReflectionUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import javax.annotation.PostConstruct;
import java.lang.reflect.Type;

@SuppressWarnings("unchecked")
public abstract class GenericRequestBodyAdvice<T> extends RequestBodyAdviceAdapter {

    private Class<T> genericParameterType;

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return this.genericParameterType == targetType;
    }

    @Override
    public final T afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return this.postProcess((T) super.afterBodyRead(body, inputMessage, parameter, targetType, converterType), parameter);
    }

    public T postProcess(T body, MethodParameter parameter) {
        return body;
    }

    @PostConstruct
    private void init() {
        this.genericParameterType = this.getGenericParameterType();
    }

    private Class<T> getGenericParameterType() {
        return (Class<T>) ReflectionUtils.getParameterizedTypesOf(this.getClass(), GenericRequestBodyAdvice.class)[0];
    }
}

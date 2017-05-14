package io.bayberry.core.controller.advice;

import io.bayberry.core.common.Error;
import io.bayberry.core.dto.JsonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class JsonResponseBodyAdvice implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return MappingJackson2HttpMessageConverter.class.equals(converterType);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        JsonResponse jsonResponse = new JsonResponse();
        if (body instanceof Error) {
            Error error = (Error) body;
            jsonResponse.setCode(error.getCode());
            jsonResponse.setMessage(error.getMessage());
        } else {
            jsonResponse.setData(body);
        }
        return jsonResponse;
    }
}

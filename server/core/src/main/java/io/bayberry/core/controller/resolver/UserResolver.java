package io.bayberry.core.controller.resolver;

import io.bayberry.core.authentication.Auth;
import io.bayberry.core.authentication.User;
import io.bayberry.core.constant.Attributes;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class UserResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return User.class.equals(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Auth auth = (Auth) webRequest.getAttribute(Attributes.AUTH, RequestAttributes.SCOPE_REQUEST);
        return auth.getUser();
    }
}

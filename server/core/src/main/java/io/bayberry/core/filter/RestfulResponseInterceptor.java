package io.bayberry.core.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RestfulResponseInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        RequestMethod requestMethod = RequestMethod.valueOf(StringUtils.upperCase(request.getMethod()));
        switch (requestMethod) {
            case POST:
                response.setStatus(HttpStatus.CREATED.value());
                break;
            case DELETE:
                response.setStatus(HttpStatus.NO_CONTENT.value());
                break;
            default:
                response.setStatus(HttpStatus.OK.value());
                break;
        }
        return true;
    }
}

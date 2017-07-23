package io.bayberry.flame.core.filter;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractAuthenticationInterceptor extends HandlerInterceptorAdapter {

    public final boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                                   Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        boolean accepted = true;

        if (!isExcluded(request, handlerMethod)) {
            accepted = isAuthenticated(request, handlerMethod);
            if (accepted)
                requestAccepted(request, response);
            else {
                requestRefused(request, response);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }

        return accepted;
    }

    protected abstract boolean isAuthenticated(HttpServletRequest request, HandlerMethod handlerMethod);

    protected abstract boolean isExcluded(HttpServletRequest request, HandlerMethod handlerMethod);

    protected abstract void requestAccepted(HttpServletRequest request, HttpServletResponse response);

    protected abstract void requestRefused(HttpServletRequest request, HttpServletResponse response);
}

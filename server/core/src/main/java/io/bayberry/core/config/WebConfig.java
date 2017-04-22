package io.bayberry.core.config;

import io.bayberry.core.controller.resolver.AuthResolver;
import io.bayberry.core.controller.resolver.UserResolver;
import io.bayberry.core.filter.RestfulResponseInterceptor;
import io.bayberry.core.filter.TokenAuthenticationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenAuthenticationInterceptor());
        registry.addInterceptor(new RestfulResponseInterceptor());
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new AuthResolver());
        argumentResolvers.add(new UserResolver());
        super.addArgumentResolvers(argumentResolvers);
    }
}

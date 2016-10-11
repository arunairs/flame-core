package cn.blm.promise.server.config;

import cn.blm.promise.server.filter.TokenAuthenticationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author jiaan.zhang@oracle.com
 * @date 08/10/2016 9:37 PM
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter
{
	@Override
	public void addInterceptors(InterceptorRegistry registry)
	{
		registry.addInterceptor(new TokenAuthenticationInterceptor());
	}
}

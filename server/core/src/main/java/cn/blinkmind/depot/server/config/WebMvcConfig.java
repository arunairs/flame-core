package cn.blinkmind.depot.server.config;

import cn.blinkmind.depot.server.filter.TokenAuthenticationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author jiaan.zhang@outlook.com
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

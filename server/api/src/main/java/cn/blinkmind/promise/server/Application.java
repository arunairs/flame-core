package cn.blinkmind.promise.server;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author jiaan.zhang@oracle.com
 * @date 9/14/16 1:48 PM
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableAspectJAutoProxy
@EnableAsync
public class Application
{
	public static void main(String[] args)
	{
		SpringApplication application = new SpringApplication(Application.class);
		application.setBannerMode(Banner.Mode.OFF);
		application.run(args);
	}
}

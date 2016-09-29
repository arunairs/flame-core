package cn.blm.promise.server.api;

import cn.blm.promise.server.repository.AbstractRepository;
import cn.blm.promise.server.repository.domain.AbstractEntity;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author jiaan.zhang@oracle.com
 * @date 9/14/16 1:48 PM
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses = {AbstractRepository.class})
@EntityScan(basePackageClasses = {AbstractEntity.class})
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

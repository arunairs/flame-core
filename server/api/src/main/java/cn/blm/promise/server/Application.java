package cn.blm.promise.server;

import cn.blm.promise.server.repository.DocumentRepository;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author jiaan.zhang@oracle.com
 * @date 9/14/16 1:48 PM
 */
@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = {DocumentRepository.class})
public class Application
{
	public static void main(String[] args)
	{
		SpringApplication application = new SpringApplication(Application.class);
		application.setBannerMode(Banner.Mode.OFF);
		application.run(args);
	}
}

package cn.blinkmind.flame.server;

import cn.blinkmind.flame.server.repository.AbstractMongoRepository;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = {AbstractMongoRepository.class})
public class Application
{
	public static void main(String[] args)
	{
		SpringApplication application = new SpringApplication(Application.class);
		application.setBannerMode(Banner.Mode.OFF);
		application.run(args);
	}
}

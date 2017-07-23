package io.bayberry.core;

import io.bayberry.core.repository.AbstractMongoRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = {AbstractMongoRepository.class})
public class FlameApplication {

    public static void main(String[] args) {
        new SpringApplication(FlameApplication.class).run(args);
    }
}

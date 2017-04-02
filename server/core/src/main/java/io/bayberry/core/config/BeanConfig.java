package io.bayberry.core.config;

import io.bayberry.repository.util.IdGenerator;
import io.bayberry.repository.util.SnowflakeIdGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public IdGenerator<Long> idGenerator(@Value("#{systemProperties['HOST_WORKER_ID']?:0}") int workerId,
                                         @Value("#{systemProperties['HOST_DATA_CENTER_ID']?:0}") int dataCenterId) {
        return new SnowflakeIdGenerator(workerId, dataCenterId);
    }
}

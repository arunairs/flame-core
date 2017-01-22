package cn.blinkmind.flame.server.config;

import cn.blinkmind.flame.server.env.Env;
import cn.blinkmind.flame.server.repository.util.IdGenerator;
import cn.blinkmind.flame.server.repository.util.SnowflakeIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig
{
    @Autowired
    private Env env;

    @Bean
    public IdGenerator<Long> idGenerator()
    {
        IdGenerator<Long> idGenerator = new SnowflakeIdGenerator(env.getHostWorkId(), env.getHostDataCenterId());
        return idGenerator;
    }
}

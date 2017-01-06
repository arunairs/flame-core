package cn.blinkmind.flame.server.config;

import cn.blinkmind.flame.server.env.Env;
import com.ge.snowizard.core.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig
{
    @Autowired
    private Env env;

    @Bean
    public IdWorker idWorker()
    {
        IdWorker idWorker = new IdWorker(env.getHostWorkId(), env.getHostDataCenterId());
        return idWorker;
    }
}

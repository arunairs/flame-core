package cn.blinkmind.depot.server;

import cn.blinkmind.depot.server.config.WorkerConfig;
import com.ge.snowizard.core.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig
{
	@Autowired
	private WorkerConfig workerConfig;

	@Bean
	public IdWorker idWorker()
	{
		IdWorker idWorker = new IdWorker(workerConfig.getWorkId(), workerConfig.getDatacenterId());
		return idWorker;
	}
}

package cn.blinkmind.depot.server;

import cn.blinkmind.depot.server.config.WorkerConfig;
import com.ge.snowizard.core.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jiaan.zhang@oracle.com
 * @date 26/09/2016 10:54 PM
 */
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

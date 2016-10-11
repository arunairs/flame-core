package cn.blm.promise.server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author jiaan.zhang@oracle.com
 * @date 04/10/2016 2:12 AM
 */
@Configuration
@ConfigurationProperties(prefix = "worker")
public class WorkerConfig
{
	private int workId = 0;
	private int datacenterId = 0;

	public int getWorkId()
	{
		return workId;
	}

	public void setWorkId(int workId)
	{
		this.workId = workId;
	}

	public int getDatacenterId()
	{
		return datacenterId;
	}

	public void setDatacenterId(int datacenterId)
	{
		this.datacenterId = datacenterId;
	}
}

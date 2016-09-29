package cn.blm.promise.server.api.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author jiaan.zhang@oracle.com
 * @date 29/09/2016 4:09 PM
 */
@Configuration
public class JpaConfig
{
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource()
	{
		return new DruidDataSource();
	}
}

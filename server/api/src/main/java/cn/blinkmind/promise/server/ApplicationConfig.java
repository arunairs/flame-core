package cn.blinkmind.promise.server;

import cn.blinkmind.promise.server.config.DataSourceConfig;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author jiaan.zhang@oracle.com
 * @date 26/09/2016 10:54 PM
 */
@Configuration
public class ApplicationConfig
{
	@Autowired
	private DataSourceConfig dataSourceConfig;

	@Bean
	public DataSource getDataSource() throws SQLException
	{
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(dataSourceConfig.getDriverClassName());
		dataSource.setUrl(dataSourceConfig.getUrl());
		dataSource.setUsername(dataSourceConfig.getUsername());
		dataSource.setPassword(dataSourceConfig.getPassword());
		dataSource.setInitialSize(dataSourceConfig.getInitialSize());
		dataSource.setMinIdle(dataSourceConfig.getMinIdle());
		dataSource.setMaxActive(dataSourceConfig.getMaxActive());
		dataSource.setMaxWait(dataSourceConfig.getMaxWait());
		dataSource.setTimeBetweenConnectErrorMillis(dataSourceConfig.getTimeBetweenEvictionRunsMillis());
		dataSource.setMinEvictableIdleTimeMillis(dataSourceConfig.getMinEvictableIdleTimeMillis());
		dataSource.setTestWhileIdle(dataSourceConfig.getTestWhileIdle());
		dataSource.setTestOnBorrow(dataSourceConfig.getTestOnBorrow());
		dataSource.setTestOnReturn(dataSourceConfig.getTestOnReturn());
		dataSource.setPoolPreparedStatements(dataSourceConfig.getPoolPreparedStatements());
		dataSource.setMaxPoolPreparedStatementPerConnectionSize(dataSourceConfig.getMaxPoolPreparedStatementPerConnectionSize());
		dataSource.setDefaultAutoCommit(dataSourceConfig.getDefaultAutoCommit());
		dataSource.setValidationQuery(dataSourceConfig.getValidationQuery());
		dataSource.setFilters(dataSourceConfig.getFilters());
		return dataSource;
	}
}

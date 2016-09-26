package cn.blinkmind.promise.server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotNull;

/**
 * @author jiaan.zhang@oracle.com
 * @date 26/09/2016 10:52 PM
 */
@Configuration
@ConfigurationProperties(prefix = "datasource")
public final class DataSourceConfig
{
	@NotNull
	private String driverClassName;

	@NotNull
	private String url;

	@NotNull
	private String username;

	@NotNull
	private String password;

	private Integer initialSize = 1;
	private Integer minIdle = 1;
	private Integer maxActive = 20;
	private Long maxWait = 60000L;
	private Long timeBetweenEvictionRunsMillis = 60000L;
	private Long minEvictableIdleTimeMillis = 300000L;
	private Boolean testWhileIdle = true;
	private Boolean testOnBorrow = true;
	private Boolean testOnReturn = false;
	private Boolean poolPreparedStatements = false;
	private Integer maxPoolPreparedStatementPerConnectionSize = 20;
	private Boolean defaultAutoCommit = false;
	private String validationQuery = "select 1 ";
	private String filters = "stat";

	public String getDriverClassName()
	{
		return driverClassName;
	}

	public String getUrl()
	{
		return url;
	}

	public String getUsername()
	{
		return username;
	}

	public String getPassword()
	{
		return password;
	}

	public Integer getInitialSize()
	{
		return initialSize;
	}

	public Integer getMinIdle()
	{
		return minIdle;
	}

	public Integer getMaxActive()
	{
		return maxActive;
	}

	public Long getMaxWait()
	{
		return maxWait;
	}

	public Long getTimeBetweenEvictionRunsMillis()
	{
		return timeBetweenEvictionRunsMillis;
	}

	public Long getMinEvictableIdleTimeMillis()
	{
		return minEvictableIdleTimeMillis;
	}

	public Boolean getTestWhileIdle()
	{
		return testWhileIdle;
	}

	public Boolean getTestOnBorrow()
	{
		return testOnBorrow;
	}

	public Boolean getTestOnReturn()
	{
		return testOnReturn;
	}

	public Boolean getPoolPreparedStatements()
	{
		return poolPreparedStatements;
	}

	public Integer getMaxPoolPreparedStatementPerConnectionSize()
	{
		return maxPoolPreparedStatementPerConnectionSize;
	}

	public Boolean getDefaultAutoCommit()
	{
		return defaultAutoCommit;
	}

	public String getValidationQuery()
	{
		return validationQuery;
	}

	public String getFilters()
	{
		return filters;
	}
}

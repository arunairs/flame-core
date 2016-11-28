package cn.blinkmind.depot.server.repository.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author jiaan.zhang@oracle.com
 * @date 08/11/2016 2:26 PM
 */
public class UrlStringBuilder
{
	private static final String SPLIT = "/";
	private final StringBuilder stringBuilder = new StringBuilder();

	public UrlStringBuilder append(String value)
	{
		if (StringUtils.isNotBlank(value))
		{
			stringBuilder.append(value);
			stringBuilder.append(SPLIT);
		}
		return this;
	}

	@Override
	public String toString()
	{
		if (stringBuilder.length() < 1) return null;
		String result = stringBuilder.toString();
		result = result.substring(0, result.length() - SPLIT.length());
		return result;
	}
}

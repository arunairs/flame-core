package cn.blinkmind.promise.server.util;

import org.apache.commons.validator.routines.UrlValidator;

/**
 * @author jiaan.zhang@oracle.com
 * @date 30/10/2016 9:49 PM
 */
public class UrlUtil
{
	private static final UrlValidator urlValidator = new UrlValidator();

	public static boolean isValid(String value)
	{
		return urlValidator.isValid(value);
	}
}

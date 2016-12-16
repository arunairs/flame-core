package cn.blinkmind.duck.server.util;

import org.apache.commons.validator.routines.UrlValidator;

public class UrlUtil
{
	private static final UrlValidator urlValidator = new UrlValidator();

	public static boolean isValid(String value)
	{
		return urlValidator.isValid(value);
	}
}

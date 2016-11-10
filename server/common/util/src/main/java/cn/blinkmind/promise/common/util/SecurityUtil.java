package cn.blinkmind.promise.common.util;

import org.apache.commons.codec.binary.Base64;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @author jiaan.zhang@oracle.com
 * @date 12/10/2016 3:55 PM
 */
public class SecurityUtil
{
	private static final Random SECURE_RANDOM = new SecureRandom();

	public static String randomSalt()
	{
		byte[] salt = new byte[32];
		SECURE_RANDOM.nextBytes(salt);
		return Base64.encodeBase64String(salt);
	}
}

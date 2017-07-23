package io.bayberry.flame.common.util;

import org.apache.commons.codec.binary.Base64;

import java.security.SecureRandom;
import java.util.Random;

public class SecurityUtils {
    private static final Random SECURE_RANDOM = new SecureRandom();

    public static String randomSalt() {
        byte[] salt = new byte[32];
        SECURE_RANDOM.nextBytes(salt);
        return Base64.encodeBase64String(salt);
    }
}

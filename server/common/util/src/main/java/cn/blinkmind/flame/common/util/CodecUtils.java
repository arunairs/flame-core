package cn.blinkmind.flame.common.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

public class CodecUtils {

    public static String sha256(final String value, final String salt) {
        return sha256(StringUtils.isNotBlank(salt) ? value + salt : value);
    }

    public static String sha256(final String value) {
        return DigestUtils.sha256Hex(value);
    }
}

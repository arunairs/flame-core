package cn.blinkmind.flame.common.util;

import com.google.common.base.Strings;
import org.apache.commons.codec.digest.DigestUtils;

public class CodecUtils {

    public static String sha256(final String value, final String salt) {
        return sha256(Strings.isNullOrEmpty(salt) ? value : value + salt);
    }

    public static String sha256(final String value) {
        return DigestUtils.sha256Hex(value);
    }
}

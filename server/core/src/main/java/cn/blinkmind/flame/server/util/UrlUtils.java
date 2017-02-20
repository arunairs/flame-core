package cn.blinkmind.flame.server.util;

import org.apache.commons.validator.routines.UrlValidator;

public class UrlUtils {
    private static final UrlValidator URL_VALIDATOR = new UrlValidator();

    public static boolean isValid(String value) {
        return URL_VALIDATOR.isValid(value);
    }
}

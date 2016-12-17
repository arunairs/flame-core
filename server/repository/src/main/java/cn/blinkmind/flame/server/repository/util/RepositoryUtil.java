package cn.blinkmind.flame.server.repository.util;

import org.springframework.data.mongodb.core.query.Update;

import java.util.Date;

public class RepositoryUtil {

    private static final String UPDATED_DATE = "updatedDate";

    public static void setUpdatedDate(Update update) {
        setProperty(update, UPDATED_DATE, new Date());
    }

    private static void setProperty(Update update, String key, Object value) {
        update.set(key, value);
    }
}

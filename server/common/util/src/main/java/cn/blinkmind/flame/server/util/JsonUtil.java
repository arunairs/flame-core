package cn.blinkmind.flame.server.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;

public class JsonUtil {

    private static final ObjectMapper _objectMapper;

    static {
        _objectMapper = new ObjectMapper();
    }

    public static <T> T deserialize(Class<T> clazz, String value) {
        return deserialize(clazz, value, _objectMapper);
    }

    public static <T> T deserialize(Class<T> clazz, String value, ObjectMapper objectMapper) {
        if (StringUtils.isBlank(value)) return null;
        try {
            return objectMapper.readValue(value, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T deserialize(Class<T> clazz, byte[] value) {
        return deserialize(clazz, new String(value, StandardCharsets.UTF_8));
    }

    public static <T> T deserialize(Class<T> clazz, byte[] value, ObjectMapper objectMapper) {
        return deserialize(clazz, new String(value, StandardCharsets.UTF_8), objectMapper);
    }

    public static <T> List<T> deserialize(Class<T> clazz, Class<? extends Collection> collectionClazz, String value, ObjectMapper objectMapper) {
        if (StringUtils.isBlank(value)) return null;
        JavaType javaType = objectMapper.getTypeFactory().constructParametrizedType(collectionClazz, List.class, clazz);
        try {
            return objectMapper.readValue(value, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> deserialize(Class<T> clazz, Class<? extends Collection> collectionClazz, String value) {
        return deserialize(clazz, collectionClazz, value, _objectMapper);
    }

    public static <T> List<T> deserialize(Class<T> clazz, Class<? extends Collection> collectionClazz, byte[] value, ObjectMapper objectMapper) {
        return deserialize(clazz, collectionClazz, new String(value, StandardCharsets.UTF_8), objectMapper);
    }

    public static <T> List<T> deserialize(Class<T> clazz, Class<? extends Collection> collectionClazz, byte[] value) {
        return deserialize(clazz, collectionClazz, new String(value, StandardCharsets.UTF_8), _objectMapper);
    }

    public static String serialize(Object object) {
        return serialize(object, _objectMapper);
    }

    public static String serialize(Object object, ObjectMapper objectMapper) {
        if (object == null) return null;
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T convert(Object object, Class<T> clazz) {
        return convert(object, clazz, _objectMapper);
    }

    public static <T> T convert(Object object, Class<T> clazz, ObjectMapper objectMapper) {
        return objectMapper.convertValue(object, clazz);
    }
}

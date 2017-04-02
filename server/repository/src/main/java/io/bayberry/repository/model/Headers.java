package io.bayberry.repository.model;

import com.google.common.collect.Maps;
import lombok.ToString;

import java.util.HashMap;

@ToString
public class Headers {
    private HashMap<String, Object> hashMap = Maps.newHashMap();

    public Boolean getBoolean(String key) {
        return this.getBoolean(key, null);
    }

    public Boolean getBoolean(String key, Boolean defaultValue) {
        return this.getOrDefault(key, defaultValue);
    }

    public Integer getInteger(String key) {
        return this.getInteger(key, null);
    }

    public Integer getInteger(String key, Integer defaultValue) {
        return this.getOrDefault(key, defaultValue);
    }

    public Long getLong(String key) {
        return this.getLong(key, null);
    }

    public Long getLong(String key, Long defaultValue) {
        return this.getOrDefault(key, defaultValue);
    }

    public String getString(String key) {
        return this.getString(key, null);
    }

    public String getString(String key, String defaultValue) {
        return this.getOrDefault(key, defaultValue);
    }

    public <T> T get(String key) {
        return this.get(key, null);
    }

    public <T> T get(String key, T defaultValue) {
        return this.getOrDefault(key, defaultValue);
    }

    public <T> Headers put(String key, T value) {
        this.hashMap.put(key, value);
        return this;
    }

    public Headers putAll(Headers headers) {
        this.hashMap.putAll(headers.hashMap);
        return this;
    }

    @SuppressWarnings("unchecked")
    private <T> T getOrDefault(String key, T defaultValue) {
        return (T) this.hashMap.getOrDefault(key, defaultValue);
    }
}

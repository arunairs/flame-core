package cn.blinkmind.flame.server.repository.entity;

import java.util.HashMap;

public class Headers extends HashMap<String, Object> {

    public Headers putBoolean(String key, Boolean value) {
        putHeader(key, value);
        return this;
    }

    public Boolean getBoolean(String key) {
        return this.getHeaderOrDefault(key, null);
    }

    public Headers putInteger(String key, Integer value) {
        putHeader(key, value);
        return this;
    }

    public Integer getInteger(String key) {
        return this.getHeaderOrDefault(key, null);
    }

    public Headers putLong(String key, Long value) {
        putHeader(key, value);
        return this;
    }

    public Long getLong(String key) {
        return this.getHeaderOrDefault(key, null);
    }

    public Headers putString(String key, String value) {
        putHeader(key, value);
        return this;
    }

    public <T> T getHeader(String key) {
        return this.getHeaderOrDefault(key, null);
    }

    @SuppressWarnings("unchecked")
    public Headers putHeader(String key, Object value) {
        super.put(key, value);
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T> T getHeaderOrDefault(String key, T defaultValue) {
        return (T) super.getOrDefault(key, defaultValue);
    }
}

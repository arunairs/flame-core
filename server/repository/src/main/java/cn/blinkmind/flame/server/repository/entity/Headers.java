package cn.blinkmind.flame.server.repository.entity;

import java.util.HashMap;

public class Headers extends HashMap<String, Object> {

    public Boolean putBoolean(String key, Boolean value) {
        return this.putHeader(key, value);
    }

    public Boolean getBoolean(String key) {
        return this.getHeaderOrDefault(key, null);
    }

    public Integer putInteger(String key, Integer value) {
        return this.putHeader(key, value);
    }

    public Integer getInteger(String key) {
        return this.getHeaderOrDefault(key, null);
    }

    public Long putLong(String key, Long value) {
        return this.putHeader(key, value);
    }

    public Long getLong(String key) {
        return this.getHeaderOrDefault(key, null);
    }

    public String putString(String key, String value) {
        return this.putHeader(key, value);
    }

    public String putString(String key) {
        return this.getHeaderOrDefault(key, null);
    }

    public <T> T putHeader(String key) {
        return this.getHeaderOrDefault(key, null);
    }

    @SuppressWarnings("unchecked")
    public <T> T putHeader(String key, T value) {
        return (T) super.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T getHeaderOrDefault(String key, T defaultValue) {
        return (T) super.getOrDefault(key, defaultValue);
    }
}

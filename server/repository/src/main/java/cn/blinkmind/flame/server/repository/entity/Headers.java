package cn.blinkmind.flame.server.repository.entity;

import java.util.HashMap;

public class Headers extends HashMap<String, Object> {
    public Boolean getBoolean(String key) {
        return this.getHeaderOrDefault(key, null);
    }

    public Integer getInteger(String key) {
        return this.getHeaderOrDefault(key, null);
    }

    public Long getLong(String key) {
        return this.getHeaderOrDefault(key, null);
    }

    public Long getString(String key) {
        return this.getHeaderOrDefault(key, null);
    }

    public <T> T getHeader(String key) {
        return this.getHeaderOrDefault(key, null);
    }

    public Headers add(String key, Object value) {
        super.put(key, value);
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T> T getHeaderOrDefault(String key, T defaultValue) {
        return (T) super.getOrDefault(key, defaultValue);
    }
}

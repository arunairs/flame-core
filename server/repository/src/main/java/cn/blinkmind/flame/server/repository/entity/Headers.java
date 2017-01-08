package cn.blinkmind.flame.server.repository.entity;

import java.util.HashMap;

public class Headers extends HashMap<String, Object>
{
    public Boolean addBoolean(String key, Boolean value)
    {
        return this.addHeader(key, value);
    }

    public Boolean getBoolean(String key)
    {
        return this.getHeaderOrDefault(key, null);
    }

    public Integer addInteger(String key, Integer value)
    {
        return this.addHeader(key, value);
    }

    public Integer getInteger(String key)
    {
        return this.getHeaderOrDefault(key, null);
    }

    public Long addLong(String key, Long value)
    {
        return this.addHeader(key, value);
    }

    public Long getLong(String key)
    {
        return this.getHeaderOrDefault(key, null);
    }

    public String addString(String key, String value)
    {
        return this.addHeader(key, value);
    }

    public String getString(String key)
    {
        return this.getHeaderOrDefault(key, null);
    }

    public <T> T getHeader(String key)
    {
        return this.getHeaderOrDefault(key, null);
    }

    @SuppressWarnings("unchecked")
    public <T> T addHeader(String key, T value)
    {
        return (T) super.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T getHeaderOrDefault(String key, T defaultValue)
    {
        return (T) super.getOrDefault(key, defaultValue);
    }
}

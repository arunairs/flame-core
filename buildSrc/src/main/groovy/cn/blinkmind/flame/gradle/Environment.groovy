package cn.blinkmind.flame.gradle

import com.sun.javafx.fxml.PropertyNotFoundException
import groovy.json.JsonSlurper
import org.apache.commons.lang3.BooleanUtils

/**
 * @author jiaan.zhang@outlook.com
 * @date 4/5/16 11:35 PM
 */
class Environment {

    static Environment getInstance() { return new Environment() }

    Properties properties = new Properties()

    String getString(String key) {
        return getString(key, null)
    }

    String getString(String key, String defaultValue) {
        return getPropertyOrDefault(key, defaultValue)
    }

    String[] getStringArray(String key, String split) {
        return getStringArray(key, split, null)
    }

    String[] getStringArray(String key, String split, String[] defaultValue) {
        String value = getString(key)
        return value ? value.split(split) : defaultValue
    }

    String[] getRequiredStringArray(String key, String split) {
        String[] value = getStringArray(key, split)
        if (!value)
            throw new PropertyNotFoundException(key)
        return value
    }

    String getRequiredString(String key) {
        String value = getString(key)
        if (!value)
            throw new PropertyNotFoundException(key)
        return value
    }

    def getJson(String key) {
        String value = getString(key)
        return value ? new JsonSlurper().parseText(value) : null
    }

    Integer getInteger(String key) {
        return getInteger(key, null)
    }

    Integer getInteger(String key, Integer defaultValue) {
        String string = getPropertyOrDefault(key, null)
        Integer integer
        try {
            integer = Integer.parseInt(string, 10)
        } catch (NumberFormatException e) {
            integer = defaultValue
        }
        return integer
    }

    int getRequiredInteger(String key) {
        Integer value = getInteger(key)
        if (!value)
            throw new PropertyNotFoundException(key)
        return value
    }

    Boolean getBoolean(String key) {
        return getBoolean(key, null)
    }

    Boolean getBoolean(String key, Boolean defaultValue) {
        String string = getPropertyOrDefault(key, null)
        Boolean bool = BooleanUtils.toBooleanObject(string)
        return bool == null ? defaultValue : bool
    }

    boolean getRequiredBoolean(String key) {
        Boolean value = getBoolean(key)
        if (value == null)
            throw new PropertyNotFoundException(key)
        return value
    }

    boolean hasProperty(String key) {
        return null == getString(key, null)
    }

    String getPropertyOrDefault(String key, String defaultValue) {
        if (System.getProperties().containsKey(key))
            return System.getProperty(key)

        if (properties.containsKey(key))
            return properties.getProperty(key)

        if (System.getenv().containsKey(key))
            return System.getenv(key)

        return defaultValue
    }

    Environment addProperties(InputStream inputStream) {
        Properties properties = new Properties()
        try {
            properties.load(inputStream)
            this.properties.putAll(properties)
        }
        catch (Exception e) {
            throw new RuntimeException(e)
        }
        return this
    }
}
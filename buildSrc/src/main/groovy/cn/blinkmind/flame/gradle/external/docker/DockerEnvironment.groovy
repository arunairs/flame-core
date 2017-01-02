package cn.blinkmind.flame.gradle.external.docker

import cn.blinkmind.flame.gradle.Environment

import java.text.MessageFormat

class DockerEnvironment {
    static final String PREFIX = "DOCKER_"
    static final Environment env = Environment.getInstance()

    static String getString(String pattern, String... arguments) {
        return getString(pattern, null, arguments)
    }

    static String getString(String pattern, String defaultValue, String... arguments) {
        return env.getString(buildKey(pattern, arguments), defaultValue)
    }

    static String getRequiredString(String pattern, String... arguments) {
        return env.getRequiredString(buildKey(pattern, arguments))
    }

    static String[] getStringArray(String pattern, String split, String... arguments) {
        return env.getStringArray(buildKey(pattern, arguments), split)
    }

    static String[] getRequiredStringArray(String pattern, String split, String... arguments) {
        return env.getRequiredStringArray(buildKey(pattern, arguments), split)
    }

    static Map<String, String> getStringMap(String pattern, String... arguments) {
        String[] array = env.getStringArray(buildKey(pattern, arguments), ",")
        Map<String, String> map = null
        if (array) {
            map = [:]
            array.each { item ->
                String[] keyValue = item.split(":")
                map.put(keyValue[0], keyValue[1])
            }
        }
        return map
    }

    static getJson(String pattern, String... arguments) {
        return env.getJson(buildKey(pattern, arguments))
    }

    static String buildKey(String pattern, String... arguments) {
        return MessageFormat.format(PREFIX + pattern, arguments)
    }
}
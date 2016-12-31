package cn.blinkmind.flame.gradle.external

import cn.blinkmind.flame.gradle.util.Environment

import java.text.MessageFormat

class Docker {

}

class DockerContainer {
    String name
    Map<String, String> binds
    List<String> ports
    DockerImage image
    static final String ENV_PATTERN_CONTAINER = "{0}_CONTAINER"
    static final String ENV_PATTERN_VOLUMES = "{0}_VOLUMES"
    static final String ENV_PATTERN_PORTS = "{0}_PORTS"

    static DockerContainer load(String componentName) {
        componentName = componentName.toUpperCase()
        String name = DockerEnvironment.getRequiredString(ENV_PATTERN_CONTAINER, componentName)
        Map<String, String> binds = DockerEnvironment.getStringMap(ENV_PATTERN_VOLUMES, componentName)
        List<String> ports = DockerEnvironment.getStringArray(ENV_PATTERN_PORTS, ",", componentName)
        DockerImage image = DockerImage.load(componentName)
        return new DockerContainer(name: name, binds: binds, ports: ports, image: image)
    }

    static boolean isEnabled(String componentName) {
        componentName = componentName.toUpperCase()
        return DockerEnvironment.getString(ENV_PATTERN_CONTAINER, componentName, null) != null
    }
}

class DockerImage {
    String repository
    String tag
    String id
    static final String ENV_PATTERN_IMAGE = "{0}_IMAGE"

    static DockerImage load(String componentName) {
        componentName = componentName.toUpperCase()
        String[] repositoryTag = DockerEnvironment.getRequiredStringArray(ENV_PATTERN_IMAGE, ":", componentName)
        String repository = repositoryTag[0]
        String tag = repositoryTag.length > 1 ? repositoryTag[1] : 'latest'
        String id = repository + ":" + tag
        return new DockerImage(repository: repository, tag: tag, id: id)
    }
}

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

    static String buildKey(String pattern, String... arguments) {
        return MessageFormat.format(PREFIX + pattern, arguments)
    }
}

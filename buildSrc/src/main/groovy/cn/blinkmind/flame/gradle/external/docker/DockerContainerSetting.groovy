package cn.blinkmind.flame.gradle.external.docker

class DockerContainerSetting {
    static final String ENV_PATTERN_CONTAINER = "{0}_CONTAINER"
    static final String ENV_PATTERN_CMD = "{0}_CMD"
    static final String ENV_PATTERN_VOLUMES = "{0}_VOLUMES"
    static final String ENV_PATTERN_PORTS = "{0}_PORTS"
    String name
    Map<String, String> binds
    List<String> ports
    DockerImageSetting image
    String[] cmd

    static DockerContainerSetting load(String componentName) {
        componentName = componentName.toUpperCase()
        String name = DockerEnvironment.getRequiredString(ENV_PATTERN_CONTAINER, componentName)
        Map<String, String> binds = DockerEnvironment.getStringMap(ENV_PATTERN_VOLUMES, componentName)
        List<String> ports = DockerEnvironment.getStringArray(ENV_PATTERN_PORTS, ",", componentName)
        String[] cmd = DockerEnvironment.getJson(ENV_PATTERN_CMD, componentName)
        DockerImageSetting image = DockerImageSetting.load(componentName)
        return new DockerContainerSetting(name: name, binds: binds, cmd: cmd ? cmd : null, ports: ports, image: image)
    }

    static boolean isEnabled(String componentName) {
        componentName = componentName.toUpperCase()
        return DockerEnvironment.getString(ENV_PATTERN_CONTAINER, componentName, null) != null
    }
}

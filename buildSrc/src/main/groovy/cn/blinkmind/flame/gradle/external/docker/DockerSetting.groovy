package cn.blinkmind.flame.gradle.external.docker

import org.gradle.internal.os.OperatingSystem

class DockerSetting {
    static final String ENV_PATTERN_URL = "URL"
    static final String ENV_PATTERN_REGISTRY_URL = "REGISTRY_URL"
    String url
    String registryUrl

    static DockerSetting load() {
        String url = DockerEnvironment.getString(ENV_PATTERN_URL, OperatingSystem.current().isWindows() ? 'tcp://127.0.0.1:2375' : 'unix:///var/run/docker.sock')
        String registryUrl = DockerEnvironment.getString(ENV_PATTERN_REGISTRY_URL)
        return new DockerSetting(url: url, registryUrl: registryUrl)
    }
}





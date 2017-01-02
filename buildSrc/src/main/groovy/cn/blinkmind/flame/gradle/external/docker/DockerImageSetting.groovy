package cn.blinkmind.flame.gradle.external.docker

class DockerImageSetting {
    static final String ENV_PATTERN_IMAGE = "{0}_IMAGE"
    String repository
    String tag
    String id

    static DockerImageSetting load(String componentName) {
        componentName = componentName.toUpperCase()
        String[] repositoryTag = DockerEnvironment.getRequiredStringArray(ENV_PATTERN_IMAGE, ":", componentName)
        String repository = repositoryTag[0]
        String tag = repositoryTag.length > 1 ? repositoryTag[1] : 'latest'
        String id = repository + ":" + tag
        return new DockerImageSetting(repository: repository, tag: tag, id: id)
    }
}
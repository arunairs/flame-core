package cn.blinkmind.promise.buildsrc.container

import org.gradle.api.Project

/**
 * @author jiaan.zhang@oracle.com
 * @date 06/10/2016 12:46 AM
 */
abstract class Container {
    enum ContainerState {
        RUNNING,
        STOPPED
    }

    String port = null
    String version = null
    String name = null
    String host = null
    String homeDir = null
    Project project = null
    Map<String, Object> properties = new HashMap<String, Object>();

    public ContainerConfig(String name, Project project) {
        this.name = name
        this.project = project
    }

    abstract isAlive()

    abstract getStatus()

    abstract start()

    abstract stop()

    abstract deploy(Object artifact)

    abstract undeploy(Object artifact)
}

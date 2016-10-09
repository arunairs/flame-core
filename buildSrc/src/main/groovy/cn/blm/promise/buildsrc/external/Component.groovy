package cn.blm.promise.buildsrc.external

import org.gradle.api.Project

/**
 * @author jiaan.zhang@oracle.com
 * @date 06/10/2016 1:50 AM
 */
abstract class Component {

    enum State {
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

    Component(String name, Project project) {
        this.name = name
        this.project = project
    }

    abstract isAlive()

    abstract getStatus()

    abstract start()

    abstract stop()
}

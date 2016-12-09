package cn.blinkmind.depot.gradle.external

import cn.blinkmind.depot.gradle.util.Environment
import cn.blinkmind.depot.gradle.util.Builder
import org.gradle.api.Project

/**
 * @author jiaan.zhang@outlook.com
 * @date 06/10/2016 1:50 AM
 */
abstract class Component {

    enum Status {
        RUNNING,
        STOPPED
    }

    static final Environment environment = new Environment()
    String name = null
    String host = null
    String homeDir = null
    Integer port = null
    String version = null
    Project project = null
    Map<String, Object> properties = new HashMap<String, Object>();

    Component(String name, Project project) {
        this(name, project, null)
    }

    Component(String name, Project project, Settings settings) {
        this.name = name
        this.project = project
        if (settings) {
            this.homeDir = settings.homeDir
            this.host = settings.host
            this.port = settings.port
            this.version = settings.version
        }
    }

    abstract boolean isAlive()

    abstract String type()

    abstract void execute(Status status)

    Status getStatus() {
        return isAlive() ? Status.RUNNING : Status.STOPPED
    }

    void start(Long timeout = 10 * 1000) {
        println "Starting ${type()}:${name} at ${host}:${port} ..."
        if (!switchStatus(Status.RUNNING, timeout))
            Builder.fail("Fail to start ${type()}:${name} in ${timeout / 1000} seconds.")
        else
            println "${type()}:${name} is started."
    }

    boolean switchStatus(Status targetStatus, long timeout) {
        long startTime = System.currentTimeMillis()
        while (getStatus() != targetStatus && (System.currentTimeMillis() - startTime <= timeout)) {
            Thread.sleep(1000)
            execute(targetStatus)
            if(targetStatus==Status.STOPPED)break
        }
        return System.currentTimeMillis() - startTime <= timeout
    }

    void stop(Long timeout = 10 * 1000) {
        println "Stopping ${type()}:${name} at ${host}:${port} ..."
        if (!switchStatus(Status.STOPPED, timeout))
            Builder.fail("Fail to stop ${type()}:${name} in ${timeout / 1000} seconds.")
        else
            println "${type()}:${name} is stopped."
    }

    static class Settings {
        String homeDir = null
        String host = null
        Integer port = null
        String version = null
    }

}

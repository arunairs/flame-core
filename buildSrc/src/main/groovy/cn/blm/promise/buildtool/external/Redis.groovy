package cn.blm.promise.buildtool.external

import cn.blm.promise.buildtool.Runner
import org.gradle.api.Project
import redis.clients.jedis.Jedis
import cn.blm.promise.buildtool.Runtime

/**
 * @author jiaan.zhang@oracle.com
 * @date 06/10/2016 12:46 AM
 */
class Redis extends Component {

    static final long TIMEOUT = 60 * 1000
    static final String REDIS_SERVER = 'redis-server'
    static final String PONG = 'PONG'

    Jedis client;
    String config
    Integer database

    Redis(String name, Project project, Settings settings) {
        super(name, project, settings)
        this.config = settings.config
        this.database = settings.database
        this.client = new Jedis(host, Integer.parseInt(port, 10))
    }

    @Override
    boolean isAlive() {
        boolean isAlive = true
        try {
            if (!client.ping().trim().equalsIgnoreCase(PONG))
                isAlive = false
        } catch (Exception e) {
            isAlive = false
        }
        return isAlive
    }

    @Override
    void start() {
        println "Starting redis..."
        boolean success = waitStatus(Status.STOPPED, 5 * 1000)
        if (!success)
            println "Redis is already running"
        else {
            controlRedis(Status.RUNNING)
            success = waitStatus(Status.RUNNING, TIMEOUT)
            if (!success)
                Runner.fail("Fail to start redis in ${TIMEOUT / 1000} seconds")
        }
        client.select(this.database)
        controlRedis(Status.RUNNING)
    }

    @Override
    void stop() {
        println "Stopping redis..."
        boolean success = waitStatus(Status.RUNNING, 5 * 1000)
        if (!success)
            println "Redis is already stopped"
        else {
            controlRedis(Status.STOPPED)
            success = waitStatus(Status.STOPPED, TIMEOUT)
            if (!success)
                Runner.fail("Fail to stop redis in ${TIMEOUT / 1000} seconds")
        }
    }

    def waitStatus(Status state, long timeout = 10 * 1000) {
        long startTime = System.currentTimeMillis()
        switch (state) {
            case Status.RUNNING:
                while (!isAlive() && (System.currentTimeMillis() - startTime <= timeout)) {
                    Thread.sleep(1000)
                    controlRedis(Status.RUNNING)
                }
                break
            case Status.STOPPED:
                while (isAlive() && (System.currentTimeMillis() - startTime <= timeout)) {
                    Thread.sleep(1000)
                    controlRedis(Status.STOPPED)
                }
                break
        }
        return System.currentTimeMillis() - startTime <= timeout
    }

    def controlRedis(Status state) {
        switch (state) {
            case Status.RUNNING:
                if (this.config)
                    executeScript(["${homeDir}${File.separator}${REDIS_SERVER}", "${this.config}", "--port", port] as String[])
                else
                    executeScript(["${homeDir}${File.separator}${REDIS_SERVER}", "--port", port] as String[])
                break
            case Status.STOPPED:
                client.shutdown()
                client = null
                client = new Jedis(host, Integer.parseInt(port, 10))
                break
        }
    }

    def executeScript(String[] commands) {
        Runtime.run(commands, new File(homeDir), [:], false)
    }

    static class Settings extends Component.Settings {
        String config
        Integer database
    }
}

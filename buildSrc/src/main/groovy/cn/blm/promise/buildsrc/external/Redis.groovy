package cn.blm.promise.buildsrc.external

import cn.blm.promise.buildsrc.Runner
import org.gradle.api.Project
import redis.clients.jedis.Jedis
import cn.blm.promise.buildsrc.Runtime

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

    Redis(String name, String host, String port, Integer database, Project project) {
        super(name, project)
        this.host = host
        this.port = port
        this.database = database

        if (!this.homeDir)
            this.homeDir = System.getenv("REDIS_HOME")

        if (!this.homeDir)
            Runner.fail("REDIS_HOME is not set")

        if (!this.host)
            this.host = System.getenv("REDIS_HOST")
        if (!this.port)
            this.port = System.getenv("REDIS_PORT")

        if (!this.host)
            this.host = '127.0.0.1'
        if (!this.port)
            this.port = '6379'

        this.config = System.getenv("REDIS_CONFIG")
        this.client = new Jedis(host, Integer.parseInt(port, 10))
    }

    @Override
    def isAlive() {
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
    def getStatus() {
        return isAlive() ? State.RUNNING : State.STOPPED
    }

    @Override
    def start() {
        println "Starting redis..."
        boolean success = waitStatus(State.STOPPED, 5 * 1000)
        if (!success)
            println "Redis is already running"
        else {
            controlRedis(State.RUNNING)
            success = waitStatus(State.RUNNING, TIMEOUT)
            if (!success)
                Runner.fail("Fail to start redis in ${TIMEOUT / 1000} seconds")
        }
        client.select(this.database)
        controlRedis(State.RUNNING)
    }

    @Override
    def stop() {
        return null
    }

    def waitStatus(State state, long timeout = 10 * 1000) {
        long startTime = System.currentTimeMillis()
        switch (state) {
            case State.RUNNING:
                while (!isAlive() && (System.currentTimeMillis() - startTime <= timeout)) {
                    Thread.sleep(1000)
                    controlRedis(State.RUNNING)
                }
                break
            case State.STOPPED:
                while (isAlive() && (System.currentTimeMillis() - startTime <= timeout)) {
                    Thread.sleep(1000)
                    controlRedis(State.STOPPED)
                }
                break
        }
        return System.currentTimeMillis() - startTime <= timeout
    }

    def controlRedis(State state) {
        switch (state) {
            case State.RUNNING:
                if (this.config)
                    executeScript(["${homeDir}${File.separator}${REDIS_SERVER}", "${this.config}", "--port", port] as String[])
                else
                    executeScript(["${homeDir}${File.separator}${REDIS_SERVER}", "--port", port] as String[])
                break
            case State.STOPPED:
                client.shutdown()
                client = null
                client = new Jedis(host, Integer.parseInt(port, 10))
                break
        }
    }

    def executeScript(String[] commands) {
        Runtime.run(commands, new File(homeDir), [:], false)
    }
}

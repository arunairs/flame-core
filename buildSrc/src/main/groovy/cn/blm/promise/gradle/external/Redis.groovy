package cn.blm.promise.gradle.external

import cn.blm.promise.gradle.util.Runtime
import org.gradle.api.Project
import redis.clients.jedis.Jedis

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
        this.client = new Jedis(host, port)
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
    String type() {
        return "Redis"
    }

    @Override
    void execute(Status status) {
        switch (status) {
            case Status.RUNNING:
                if (this.config)
                    executeScript(["${homeDir}${File.separator}${REDIS_SERVER}", "${this.config}", "--port", port] as String[])
                else
                    executeScript(["${homeDir}${File.separator}${REDIS_SERVER}", "--port", port] as String[])
                break
            case Status.STOPPED:
                client.shutdown()
                client = new Jedis(host, port, 10)
                break
        }
    }

    @Override
    void start(Long timeout) {
        super.start(timeout)
        client.select(this.database)
    }

    void executeScript(String[] commands) {
        Runtime.run(commands, new File(homeDir), [:], false)
    }

    static class Settings extends Component.Settings {
        String config
        Integer database
    }
}

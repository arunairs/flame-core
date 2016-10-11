package cn.blm.promise.gradle.external

import cn.blm.promise.gradle.util.Runtime
import com.mongodb.BasicDBObject
import com.mongodb.MongoClient
import com.mongodb.MongoClientOptions
import com.mongodb.ServerAddress
import org.gradle.api.Project

/**
 * @author jiaan.zhang@oracle.com
 * @date 10/10/2016 12:38 AM
 */
class Mongo extends Component {

    static final BasicDBObject COMMAND_PING = new BasicDBObject("ping", "1")
    static final BasicDBObject COMMAND_SHUTDOWN = new BasicDBObject("shutdown", "1")
    static {
        COMMAND_SHUTDOWN.put("force", true)
    }
    static final String MONGOD = "mongod"

    String database
    String dataDir
    MongoClient client

    Mongo(String name, Project project, Settings settings) {
        super(name, project, settings)
        this.dataDir = settings.dataDir
        this.database = settings.database
    }

    @Override
    boolean isAlive() {
        boolean isAlive = true
        try {
            getClient().getDatabase(this.database).runCommand(COMMAND_PING)
        } catch (Exception e) {
            isAlive = false
        }
        return isAlive
    }

    @Override
    String type() {
        return "Mongo"
    }

    @Override
    void execute(Status status) {
        switch (status) {
            case Status.RUNNING:
                executeScript([MONGOD, "--dbpath", dataDir, "--port", port] as String[])
                break
            case Status.STOPPED:
                try {
                    getClient().getDatabase("admin").runCommand(COMMAND_SHUTDOWN)
                } catch (Exception ignored) {
                }
                break
        }
    }

    void executeScript(String[] commands) {
        Runtime.run(commands, new File("${homeDir}${File.separator}"), [:], false)
    }

    MongoClient newClient() {
        MongoClientOptions mongoClientOptions = MongoClientOptions.builder()
                .serverSelectionTimeout(1000)
                .build();
        this.client = new MongoClient(new ServerAddress(this.host, this.port), mongoClientOptions)
    }

    MongoClient getClient() {
        if (this.client) return this.client
        return newClient()
    }

    static class Settings extends Component.Settings {
        String database
        String dataDir
    }
}

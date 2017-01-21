package cn.blinkmind.flame.server.repository.query;

public interface Keys
{
    String ID = "_id";
    String ARCHIVE = "archive";
    String HEADERS = "headers";

    interface Headers
    {
        String VERSION = "headers.version";
    }
}

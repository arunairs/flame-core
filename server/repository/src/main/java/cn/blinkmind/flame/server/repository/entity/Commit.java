package cn.blinkmind.flame.server.repository.entity;

public interface Commit<T> {

    String VERSION = "version";

    Headers getHeaders();

    T getPayload();
}

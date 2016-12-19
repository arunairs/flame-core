package cn.blinkmind.flame.server.repository.entity;

public interface Resource<ID> extends Locatable {

    Resource<ID> getParent();
}

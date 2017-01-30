package cn.blinkmind.flame.server.repository.entity;

import java.io.Serializable;

public interface Resource<ID extends Serializable> extends Locatable {

    Resource<ID> getParent();
}

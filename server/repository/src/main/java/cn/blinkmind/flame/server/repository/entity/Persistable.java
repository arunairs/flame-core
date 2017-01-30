package cn.blinkmind.flame.server.repository.entity;

import java.io.Serializable;

public interface Persistable<ID extends Serializable> extends Serializable {

    void setId(ID id);

    ID getId();
}

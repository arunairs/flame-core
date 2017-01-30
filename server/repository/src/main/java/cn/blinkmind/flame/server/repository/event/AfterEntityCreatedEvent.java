package cn.blinkmind.flame.server.repository.event;

import cn.blinkmind.flame.server.repository.entity.Persistable;

import java.io.Serializable;

public class AfterEntityCreatedEvent<ID extends Serializable> extends RepositoryEvent<Persistable<ID>> {

    public AfterEntityCreatedEvent(Persistable<ID> source) {
        super(source);
    }
}

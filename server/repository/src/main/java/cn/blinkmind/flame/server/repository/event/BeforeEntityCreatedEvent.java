package cn.blinkmind.flame.server.repository.event;

import cn.blinkmind.flame.server.repository.entity.Persistable;

import java.io.Serializable;

public class BeforeEntityCreatedEvent<ID extends Serializable> extends RepositoryEvent<Persistable<ID>> {

    public BeforeEntityCreatedEvent(Persistable<ID> source) {
        super(source);
    }
}

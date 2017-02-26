package cn.blinkmind.flame.repository.event;

import cn.blinkmind.flame.repository.model.Persistable;

import java.io.Serializable;

public class BeforeEntityUpdatedEvent<ID extends Serializable> extends RepositoryEvent<Persistable<ID>> {

    public BeforeEntityUpdatedEvent(Persistable<ID> source) {
        super(source);
    }
}

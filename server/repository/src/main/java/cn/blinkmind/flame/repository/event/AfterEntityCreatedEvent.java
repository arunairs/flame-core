package cn.blinkmind.flame.repository.event;

import cn.blinkmind.flame.repository.model.Persistable;

import java.io.Serializable;

public class AfterEntityCreatedEvent<ID extends Serializable> extends RepositoryEvent<Persistable<ID>> {

    public AfterEntityCreatedEvent(Persistable<ID> source) {
        super(source);
    }
}

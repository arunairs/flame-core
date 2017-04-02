package io.bayberry.repository.event;

import io.bayberry.repository.model.Persistable;

import java.io.Serializable;

public class AfterEntityCreatedEvent<ID extends Serializable> extends RepositoryEvent<Persistable<ID>> {

    public AfterEntityCreatedEvent(Persistable<ID> source) {
        super(source);
    }
}

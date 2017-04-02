package io.bayberry.repository.event;

import io.bayberry.repository.model.Persistable;

import java.io.Serializable;

public class BeforeEntityCreatedEvent<ID extends Serializable> extends RepositoryEvent<Persistable<ID>> {

    public BeforeEntityCreatedEvent(Persistable<ID> source) {
        super(source);
    }
}

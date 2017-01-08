package cn.blinkmind.flame.server.repository.event;

import cn.blinkmind.flame.server.repository.entity.Persistable;

import java.io.Serializable;

public class BeforeEntityUpdatedEvent<ID extends Serializable> extends RepositoryEvent<Persistable<ID>>
{
    public BeforeEntityUpdatedEvent(Persistable<ID> source)
    {
        super(source);
    }
}

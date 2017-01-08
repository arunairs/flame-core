package cn.blinkmind.flame.server.repository.event;

import cn.blinkmind.flame.server.repository.entity.Persistable;

import java.io.Serializable;

public class AfterEntityUpdatedEvent<ID extends Serializable> extends RepositoryEvent<Persistable<ID>>
{
    public AfterEntityUpdatedEvent(Persistable<ID> source)
    {
        super(source);
    }
}

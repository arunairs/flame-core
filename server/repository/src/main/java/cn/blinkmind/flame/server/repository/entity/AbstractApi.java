package cn.blinkmind.flame.server.repository.entity;

import java.util.Collection;

public abstract class AbstractApi extends AbstractPersistableArchiveNameNode implements Api {

    @Override
    public Collection<? extends Node> getChildren() {
        return null;
    }
}

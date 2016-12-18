package cn.blinkmind.flame.server.repository.entity;

import java.util.Collection;

public interface Resource<ID> extends Locatable {

    Resource<ID> getParent();

    Collection<ID> getChildrenId();
}

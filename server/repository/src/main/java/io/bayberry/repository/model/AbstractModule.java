package io.bayberry.repository.model;

import lombok.ToString;

import java.util.Collection;

@ToString(callSuper = true)
public abstract class AbstractModule extends AbstractArchiveNode {
    public abstract Collection<? extends AbstractModule> getModules();

    public abstract Collection<? extends AbstractApi> getApis();
}

package io.bayberry.repository.entity;

import lombok.ToString;

import java.util.Collection;

@ToString(callSuper = true)
public abstract class AbstractArchive extends AbstractArchiveNode {
    public abstract Collection<? extends AbstractModule> getModules();
}

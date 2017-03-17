package cn.blinkmind.flame.repository.model;

import lombok.ToString;

import java.util.Collection;

@ToString(callSuper = true)
public abstract class AbstractArchive extends AbstractArchiveNode {
    public abstract Collection<? extends AbstractModule> getModules();
}

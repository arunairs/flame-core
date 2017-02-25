package cn.blinkmind.flame.repository.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.Collection;

public abstract class AbstractArchive extends AbstractArchiveNode {
    public abstract Collection<AbstractModule> getModules();

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}

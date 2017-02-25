package cn.blinkmind.flame.repository.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.Collection;

public abstract class AbstractModule extends AbstractArchiveNode {
    public abstract Collection<AbstractModule> getModules();

    public abstract Collection<AbstractApi> getApis();

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}

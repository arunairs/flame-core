package cn.blinkmind.flame.repository.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@Getter
@Setter
public abstract class AbstractApi extends AbstractPersistableArchiveNameNode implements Api {
    private Module module;

    @Override
    public Module getParent() {
        return module;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}

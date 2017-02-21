package cn.blinkmind.flame.repository.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.List;

@Getter
@Setter
public class Module extends AbstractPersistableArchiveNameNode {
    private Node parent;
    private List<Module> modules;
    private List<Api> apis;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}

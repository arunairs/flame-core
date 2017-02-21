package cn.blinkmind.flame.repository.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@Getter
@Setter
public abstract class AbstractArchiveNode implements ArchiveNode {
    private String path;
    private String description;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}

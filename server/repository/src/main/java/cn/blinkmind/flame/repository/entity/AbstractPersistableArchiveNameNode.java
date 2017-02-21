package cn.blinkmind.flame.repository.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public abstract class AbstractPersistableArchiveNameNode extends AbstractArchiveNode implements NameNode, Persistable<Long> {
    @Id
    private Long id;
    private String name;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}

package cn.blinkmind.flame.repository.model;

import cn.blinkmind.flame.common.protocol.Request;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public abstract class AbstractArchiveNode implements Describable, Persistable<Long> {
    @Id
    private Long id;
    private String name;
    private String description;
    public abstract Request getRequest();

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}

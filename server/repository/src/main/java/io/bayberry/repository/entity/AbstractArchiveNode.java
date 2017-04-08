package io.bayberry.repository.entity;

import io.bayberry.common.protocol.Request;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@ToString(callSuper = true)
public abstract class AbstractArchiveNode implements Describable, Persistable<Long> {
    @Id
    private Long id;
    private String name;
    private String description;
    public abstract Request getRequest();
}

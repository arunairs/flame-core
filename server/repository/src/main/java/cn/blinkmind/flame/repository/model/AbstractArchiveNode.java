package cn.blinkmind.flame.repository.model;

import cn.blinkmind.flame.common.protocol.Request;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@ToString
public abstract class AbstractArchiveNode implements Describable, Persistable<Long> {
    @Id
    private Long id;
    private String name;
    private String description;
    public abstract Request getRequest();
}

package cn.blinkmind.flame.repository.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public abstract class BaseEntity<ID extends Serializable> implements Persistable<ID> {
    @Id
    private ID id;
    private Ref<ID> creatorRef;
    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}

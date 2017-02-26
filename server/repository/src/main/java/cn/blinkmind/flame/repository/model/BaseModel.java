package cn.blinkmind.flame.repository.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public abstract class BaseModel<ID extends Serializable> implements Persistable<ID> {
    @Id
    private ID id;
    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;
    private Ref<ID> creatorRef;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}

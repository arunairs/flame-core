package cn.blinkmind.flame.repository.model;

import lombok.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Ref<ID extends Serializable> {
    private ID id;

    public Ref(Persistable<ID> persistable) {
        this(persistable.getId());
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}

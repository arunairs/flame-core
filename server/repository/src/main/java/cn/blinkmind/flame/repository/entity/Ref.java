package cn.blinkmind.flame.repository.entity;

import lombok.*;

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
}

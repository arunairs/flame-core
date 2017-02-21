package cn.blinkmind.flame.repository.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@Getter
@Setter
public abstract class Account extends BaseEntity<Long> {
    private String username;
    private String password;
    private String salt;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}

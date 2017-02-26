package cn.blinkmind.flame.repository.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@Getter
@Setter
public abstract class Account extends BaseModel<Long> {
    private String username;
    private String password;
    private String salt;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}

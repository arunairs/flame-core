package cn.blinkmind.flame.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@Getter
@Setter
public abstract class Account extends BaseModel<Long> {
    private String username;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private String salt;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}

package cn.blinkmind.flame.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public abstract class Account extends BaseModel<Long> {
    private String username;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private String salt;
}

package cn.blinkmind.flame.core.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO extends BaseDTO {
    private String username;
    private String password;
    private String salt;
}

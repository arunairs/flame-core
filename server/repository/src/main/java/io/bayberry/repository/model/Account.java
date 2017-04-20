package io.bayberry.repository.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Account extends BaseModel {

    private String username;
    private String password;
    private String salt;
}

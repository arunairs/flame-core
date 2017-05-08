package io.bayberry.repository.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Account extends BaseEntity {

    private String username;
    private String password;
    private String salt;
}

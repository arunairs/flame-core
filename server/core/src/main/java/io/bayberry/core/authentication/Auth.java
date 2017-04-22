package io.bayberry.core.authentication;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Auth {

    private String token;
    private User user;
}

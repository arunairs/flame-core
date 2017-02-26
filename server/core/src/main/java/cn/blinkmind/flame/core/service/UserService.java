package cn.blinkmind.flame.core.service;

import cn.blinkmind.flame.repository.model.User;

import java.util.Optional;

public interface UserService {
    User create(final User input);

    Optional<User> get(final Long id);
}

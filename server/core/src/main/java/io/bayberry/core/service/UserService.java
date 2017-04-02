package io.bayberry.core.service;

import io.bayberry.repository.model.User;

import java.util.Optional;

public interface UserService {
    User create(final User user);

    Optional<User> get(final Long id);
}

package io.bayberry.core.service.impl;

import io.bayberry.common.util.CodecUtils;
import io.bayberry.common.util.SecurityUtils;
import io.bayberry.core.exception.Errors;
import io.bayberry.core.service.UserService;
import io.bayberry.repository.UserRepository;
import io.bayberry.repository.model.User;
import io.bayberry.core.common.validation.Matcher;
import io.bayberry.core.common.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static io.bayberry.core.common.validation.Validator.orElseThrow;
import static io.bayberry.core.common.validation.Validator.validateThat;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        Validator.validateThat(user.getUsername(), Matcher.not(Matcher.blank()), Validator.orElseThrow(Errors.ACCOUNT_NAME_IS_BLANK))
                .and(user.getPassword(), Matcher.not(Matcher.blank()), Validator.orElseThrow(Errors.ACCOUNT_PASSWORD_IS_BLANK));

        String salt = SecurityUtils.randomSalt();
        User output = new User();
        output.setUsername(user.getUsername());
        output.setSalt(salt);
        output.setPassword(CodecUtils.sha256(user.getPassword(), salt));
        this.userRepository.insert(output);
        return output;
    }

    @Override
    public Optional<User> get(Long id) {
        return null;
    }
}

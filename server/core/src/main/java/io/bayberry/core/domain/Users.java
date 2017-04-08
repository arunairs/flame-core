package io.bayberry.core.domain;

import io.bayberry.common.util.CodecUtils;
import io.bayberry.common.util.SecurityUtils;
import io.bayberry.core.common.validation.Matcher;
import io.bayberry.core.common.validation.Validator;
import io.bayberry.core.exception.Errors;
import io.bayberry.repository.UserRepository;
import io.bayberry.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Users {
    private final UserRepository userRepository;

    @Autowired
    public Users(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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

    public Optional<User> get(Long id) {
        return null;
    }
}

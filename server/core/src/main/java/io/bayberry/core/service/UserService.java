package io.bayberry.core.service;

import io.bayberry.common.util.CodecUtils;
import io.bayberry.common.util.SecurityUtils;
import io.bayberry.repository.UserRepository;
import io.bayberry.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User userEntity) {
//        Validator.validateThat(userEntity.getUsername(), Matcher.not(Matcher.blank()), Validator.orElseThrow(Errors.ACCOUNT_NAME_IS_BLANK))
//                .and(userEntity.getPassword(), Matcher.not(Matcher.blank()), Validator.orElseThrow(Errors.ACCOUNT_PASSWORD_IS_BLANK));

        String salt = SecurityUtils.randomSalt();
        User output = new User();
        output.setUsername(userEntity.getUsername());
        output.setSalt(salt);
        output.setPassword(CodecUtils.sha256(userEntity.getPassword(), salt));
        this.userRepository.insert(output);
        return output;
    }

    public Optional<User> get(Long id) {
        return null;
    }
}

package cn.blinkmind.flame.core.service.impl;

import cn.blinkmind.flame.common.util.CodecUtils;
import cn.blinkmind.flame.common.util.SecurityUtils;
import cn.blinkmind.flame.core.exception.Errors;
import cn.blinkmind.flame.core.service.UserService;
import cn.blinkmind.flame.repository.UserRepository;
import cn.blinkmind.flame.repository.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static cn.blinkmind.flame.core.common.validation.Matcher.blank;
import static cn.blinkmind.flame.core.common.validation.Matcher.not;
import static cn.blinkmind.flame.core.common.validation.Validator.orElseThrow;
import static cn.blinkmind.flame.core.common.validation.Validator.validateThat;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        validateThat(user.getUsername(), not(blank()), orElseThrow(Errors.ACCOUNT_NAME_IS_BLANK))
                .and(user.getPassword(), not(blank()), orElseThrow(Errors.ACCOUNT_PASSWORD_IS_BLANK));

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

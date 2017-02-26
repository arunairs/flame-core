package cn.blinkmind.flame.core.service.impl;

import cn.blinkmind.flame.common.util.CodecUtils;
import cn.blinkmind.flame.common.util.SecurityUtils;
import cn.blinkmind.flame.core.common.util.Assert;
import cn.blinkmind.flame.core.exception.Errors;
import cn.blinkmind.flame.core.service.UserService;
import cn.blinkmind.flame.repository.UserRepository;
import cn.blinkmind.flame.repository.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User input) {
        Assert.isNotBlank(input.getUsername(), Errors.ACCOUNT_NAME_IS_BLANK);
        Assert.isNotBlank(input.getPassword(), Errors.ACCOUNT_PASSWORD_IS_BLANK);

        String salt = SecurityUtils.randomSalt();
        User user = new User();
        user.setUsername(input.getUsername());
        user.setSalt(salt);
        user.setPassword(CodecUtils.sha256(input.getPassword(), salt));
        this.userRepository.insert(user);
        return user;
    }

    @Override
    public Optional<User> get(Long id) {
        return null;
    }
}

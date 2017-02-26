package cn.blinkmind.flame.core.service.impl;

import cn.blinkmind.flame.core.common.util.Assert;
import cn.blinkmind.flame.common.util.CodecUtils;
import cn.blinkmind.flame.core.dto.UserDTO;
import cn.blinkmind.flame.core.exception.Errors;
import cn.blinkmind.flame.core.service.UserService;
import cn.blinkmind.flame.repository.UserRepository;
import cn.blinkmind.flame.repository.model.User;
import cn.blinkmind.flame.common.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Long create(final UserDTO userDTO) {
        Assert.isNotBlank(userDTO.getUsername(), Errors.ACCOUNT_NAME_IS_BLANK);
        Assert.isNotBlank(userDTO.getPassword(), Errors.ACCOUNT_PASSWORD_IS_BLANK);

        String salt = SecurityUtils.randomSalt();
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setSalt(salt);
        user.setPassword(CodecUtils.sha256(userDTO.getPassword(), salt));
        this.userRepository.insert(user);
        return user.getId();
    }
}

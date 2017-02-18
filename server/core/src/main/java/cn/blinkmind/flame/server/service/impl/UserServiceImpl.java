package cn.blinkmind.flame.server.service;

import cn.blinkmind.flame.server.util.Assert;
import cn.blinkmind.flame.server.exception.Errors;
import cn.blinkmind.flame.server.repository.UserDAO;
import cn.blinkmind.flame.server.repository.entity.User;
import cn.blinkmind.flame.server.util.CodecUtil;
import cn.blinkmind.flame.server.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDAO userRepository;

    public User create(final User rawData) {
        Assert.notBlank(rawData.getUsername(), Errors.ACCOUNT_NAME_IS_BLANK);
        Assert.notBlank(rawData.getPassword(), Errors.ACCOUNT_PASSWORD_IS_BLANK);

        User user = new User();
        user.setUsername(rawData.getUsername());
        user.setSalt(SecurityUtil.randomSalt());
        user.setPassword(CodecUtil.sha256(rawData.getPassword(), user.getSalt()));
        userRepository.insert(user);
        return user;
    }
}

package cn.blinkmind.flame.server.service;

import cn.blinkmind.flame.server.exception.Errors;
import cn.blinkmind.flame.server.exception.Assertion;
import cn.blinkmind.flame.server.util.CodecUtil;
import cn.blinkmind.flame.server.repository.UserRepository;
import cn.blinkmind.flame.server.repository.entity.CrudType;
import cn.blinkmind.flame.server.repository.entity.User;
import cn.blinkmind.flame.server.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends PersistenceService
{
    @Autowired
    private UserRepository userRepository;

    public User create(User user)
    {
        Assertion.notBlank(user.getUsername(), Errors.ACCOUNT_NAME_IS_BLANK);
        Assertion.notBlank(user.getPassword(), Errors.ACCOUNT_PASSWORD_IS_BLANK);

        user.cleanup(CrudType.CREATE);
        user.setId(newId());
        user.setPassword(CodecUtil.sha256(user.getPassword(), SecurityUtil.randomSalt()));
        user.refreshCreatedDate();
        userRepository.insert(user);
        return user;
    }
}

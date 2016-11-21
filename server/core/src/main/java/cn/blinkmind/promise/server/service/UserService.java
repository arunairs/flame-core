package cn.blinkmind.promise.server.service;

import cn.blinkmind.promise.server.util.CodecUtil;
import cn.blinkmind.promise.server.util.SecurityUtil;
import cn.blinkmind.promise.server.exception.Error;
import cn.blinkmind.promise.server.exception.Errors;
import cn.blinkmind.promise.server.repository.UserRepository;
import cn.blinkmind.promise.server.repository.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jiaan.zhang@oracle.com
 * @date 13/10/2016 11:42 PM
 */
@Service
public class UserService
{
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RepositoryService repositoryService;

	public User create(User user)
	{
		if (StringUtils.isBlank(user.getUsername()))
			Error.occurs(Errors.ACCOUNT_NAME_IS_BLANK);
		if (StringUtils.isBlank(user.getPassword()))
			Error.occurs(Errors.ACCOUNT_PASSWORD_IS_BLANK);

		user.clean();
		user.setId(repositoryService.newId());
		user.setPassword(CodecUtil.sha256(user.getPassword(), SecurityUtil.randomSalt()));
		user.refreshCreatedDate();
		userRepository.insert(user);
		return user;
	}
}

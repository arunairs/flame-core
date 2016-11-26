package cn.blinkmind.promise.server.service;

import cn.blinkmind.promise.server.exception.Assertion;
import cn.blinkmind.promise.server.exception.Errors;
import cn.blinkmind.promise.server.repository.UserRepository;
import cn.blinkmind.promise.server.repository.entity.CRUD;
import cn.blinkmind.promise.server.repository.entity.User;
import cn.blinkmind.promise.server.util.CodecUtil;
import cn.blinkmind.promise.server.util.SecurityUtil;
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
		Assertion.notBlank(user.getUsername(), Errors.ACCOUNT_NAME_IS_BLANK);
		Assertion.notBlank(user.getPassword(), Errors.ACCOUNT_PASSWORD_IS_BLANK);

		user.cleanup(CRUD.CREATE);
		user.setId(repositoryService.newId());
		user.setPassword(CodecUtil.sha256(user.getPassword(), SecurityUtil.randomSalt()));
		user.refreshCreatedDate();
		userRepository.insert(user);
		return user;
	}
}

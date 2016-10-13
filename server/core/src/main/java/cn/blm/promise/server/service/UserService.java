package cn.blm.promise.server.service;

import cn.blm.promise.common.util.CodecUtil;
import cn.blm.promise.common.util.SecurityUtil;
import cn.blm.promise.server.exception.BadRequestException;
import cn.blm.promise.server.exception.Errors;
import cn.blm.promise.server.repository.UserRepository;
import cn.blm.promise.server.repository.entity.User;
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

	public User create(User userData)
	{
		if (StringUtils.isBlank(userData.getUsername()))
			throw new BadRequestException(Errors.ACCOUNT_NAME_IS_BLANK);
		if (StringUtils.isBlank(userData.getPassword()))
			throw new BadRequestException(Errors.ACCOUNT_PASSWORD_IS_BLANK);

		User user = new User();
		user.setId(repositoryService.newId());
		user.setPassword(CodecUtil.sha256(userData.getPassword(), SecurityUtil.randomSalt()));
		user.writeCreatedDate();
		userRepository.insert(user);
		return user;
	}
}

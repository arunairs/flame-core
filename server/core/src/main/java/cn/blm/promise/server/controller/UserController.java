package cn.blm.promise.server.controller;

import cn.blm.promise.server.bean.BodyWrapper;
import cn.blm.promise.server.repository.entity.User;
import cn.blm.promise.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author jiaan.zhang@oracle.com
 * @date 12/10/2016 3:11 PM
 */
@RestController
@RequestMapping(path = "users")
public class UserController
{
	@Autowired
	private UserService userService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<BodyWrapper> create(@RequestBody User userData)
	{
		User user = userService.create(userData);
		return ResponseEntity.ok(BodyWrapper.builder().id(user.getId()).build());
	}
}

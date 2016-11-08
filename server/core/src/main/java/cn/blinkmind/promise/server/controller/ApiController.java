package cn.blinkmind.promise.server.controller;

import cn.blinkmind.promise.server.annotation.Token;
import cn.blinkmind.promise.server.bean.web.ObjectId;
import cn.blinkmind.promise.server.repository.ModuleRepository;
import cn.blinkmind.promise.server.repository.entity.Api;
import cn.blinkmind.promise.server.repository.entity.Module;
import cn.blinkmind.promise.server.repository.entity.User;
import cn.blinkmind.promise.server.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author jiaan.zhang@oracle.com
 * @date 29/10/2016 10:35 PM
 */
@RestController
public class ApiController
{
	@Autowired
	private ModuleRepository moduleRepository;

	@Autowired
	private ApiService apiService;

	@Token
	@PostMapping("modules/{moduleId}/apis")
	public ResponseEntity<ObjectId> create(@PathVariable(name = "moduleId") long moduleId, @RequestBody Api apiData, @RequestAttribute User user)
	{
		Module module = moduleRepository.require(moduleId);
		Api api = apiService.fill(apiData, module, user);
		return ResponseEntity.ok(new ObjectId(api.getId()));
	}
}

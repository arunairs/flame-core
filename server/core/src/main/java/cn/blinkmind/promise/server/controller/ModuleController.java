package cn.blinkmind.promise.server.controller;

import cn.blinkmind.promise.server.annotation.Token;
import cn.blinkmind.promise.server.bean.web.ObjectId;
import cn.blinkmind.promise.server.repository.ArchiveRepository;
import cn.blinkmind.promise.server.repository.entity.Archive;
import cn.blinkmind.promise.server.repository.entity.Module;
import cn.blinkmind.promise.server.repository.entity.User;
import cn.blinkmind.promise.server.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author jiaan.zhang@oracle.com
 * @date 28/10/2016 12:26 AM
 */
@RestController
public class ModuleController
{
	@Autowired
	private ArchiveRepository archiveRepository;

	@Autowired
	private ModuleService moduleService;

	@Token
	@PostMapping(path = "archives/{archiveId}/modules")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<ObjectId> create(@PathVariable(name = "archiveId") long archiveId, @RequestBody Module moduleData, @RequestAttribute User creator)
	{
		Archive archive = archiveRepository.require(archiveId);
		Module module = moduleService.fill(moduleData, archive, creator);
		return ResponseEntity.ok(new ObjectId(module.getId()));
	}
}

package cn.blinkmind.depot.server.controller;

import cn.blinkmind.depot.server.annotation.Token;
import cn.blinkmind.depot.server.repository.BranchRepository;
import cn.blinkmind.depot.server.repository.entity.Archive;
import cn.blinkmind.depot.server.repository.entity.Branch;
import cn.blinkmind.depot.server.repository.entity.User;
import cn.blinkmind.depot.server.service.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author jiaan.zhang@oracle.com
 * @date 28/11/2016 4:02 PM
 */
@RestController
public class ArchiveController
{
	@Autowired
	private BranchRepository branchRepository;

	@Autowired
	private ArchiveService archiveService;

	@Token
	@GetMapping(path = "branches/{branchId}/archive")
	public ResponseEntity<Archive> get(@PathVariable(name = "branchId") long branchId, @RequestAttribute User user)
	{
		Branch branch = branchRepository.require(branchId);
		return ResponseEntity.ok(archiveService.get(branch, user));
	}

	@Token
	@PatchMapping(path = "branches/{branchId}/archive")
	public ResponseEntity<Void> patch(@PathVariable(name = "branchId") long branchId, @RequestBody Map<String, Object> patch, @RequestAttribute User user)
	{
		Branch branch = branchRepository.require(branchId);
		archiveService.patch(patch, branch, user);
		return ResponseEntity.ok().build();
	}
}

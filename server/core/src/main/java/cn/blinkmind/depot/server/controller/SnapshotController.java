package cn.blinkmind.depot.server.controller;

import cn.blinkmind.depot.server.annotation.Token;
import cn.blinkmind.depot.server.bean.web.ObjectId;
import cn.blinkmind.depot.server.repository.entity.Branch;
import cn.blinkmind.depot.server.repository.entity.Snapshot;
import cn.blinkmind.depot.server.repository.entity.User;
import cn.blinkmind.depot.server.repository.entity.View;
import cn.blinkmind.depot.server.service.BranchService;
import cn.blinkmind.depot.server.service.SnapshotService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author jiaan.zhang@oracle.com
 * @date 29/11/2016 4:09 PM
 */
@RestController
public class SnapshotController
{
	@Autowired
	private BranchService branchService;

	@Autowired
	private SnapshotService snapshotService;

	@Token
	@JsonView(View.WithBranchId.class)
	@PostMapping(path = "workspace/snapshots")
	public ResponseEntity<ObjectId> create(@RequestBody Snapshot snapshot, @RequestAttribute(name = "user") User user)
	{
		snapshot = snapshotService.create(snapshot, user);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ObjectId(snapshot.getId()));
	}

	@Token
	@GetMapping(path = "workspace/snapshots/archive")
	public ResponseEntity<Snapshot> getArchive(@RequestParam(name = "branchId") long branchId, @RequestAttribute(name = "user") User user)
	{
		Branch branch = branchService.require(branchId, user);
		Snapshot snapshot = snapshotService.require(branch, user);
		return ResponseEntity.ok(snapshot);
	}

	@Token
	@PutMapping(path = "workspace/snapshots/archive")
	public ResponseEntity<Void> updateArchive(@RequestParam(name = "branchId") long branchId, @RequestBody Snapshot snapshot, @RequestAttribute(name = "user") User user)
	{
		return ResponseEntity.ok().build();
	}
}

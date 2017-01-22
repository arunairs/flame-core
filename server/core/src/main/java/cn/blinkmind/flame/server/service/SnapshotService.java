package cn.blinkmind.flame.server.service;

import cn.blinkmind.flame.server.repository.entity.Headers;
import cn.blinkmind.flame.server.repository.util.IdGenerator;
import cn.blinkmind.flame.server.util.patch.JsonPatch;
import cn.blinkmind.flame.server.util.Assert;
import cn.blinkmind.flame.server.exception.Error;
import cn.blinkmind.flame.server.exception.Errors;
import cn.blinkmind.flame.server.repository.SnapshotRepository;
import cn.blinkmind.flame.server.repository.entity.Archive;
import cn.blinkmind.flame.server.repository.entity.Branch;
import cn.blinkmind.flame.server.repository.entity.Snapshot;
import cn.blinkmind.flame.server.repository.entity.User;
import cn.blinkmind.flame.server.repository.exception.ResourceNotFoundException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SnapshotService extends AbstractPersistenceService
{
    @Autowired
    private IdGenerator<Long> idGenerator;

    @Autowired
    private SnapshotRepository snapshotRepository;

    @Autowired
    private BranchService branchService;

    public Snapshot create(final Long branchId, final Snapshot rawData, final User creator)
    {
        Branch branch = branchService.require(branchId, creator, Errors.BRANCH_IS_NOT_FOUND);
        Assert.notBlank(rawData.getName(), Errors.SNAPSHOT_NAME_IS_BLANK);
        Assert.isFalse(snapshotRepository.exists(rawData.getName(), branch.getId(), creator), Errors.RESOURCE_ALREADY_EXISTS);

        Snapshot snapshot = new Snapshot();
        snapshot.setCreator(creator);
        snapshot.setName(rawData.getName());
        snapshot.setBranch(branch);
        snapshot.getHeaders().putAll(branch.getHeaders());
        snapshot.setArchive(branchService.getArchive(branch.getId(), creator));
        snapshotRepository.insert(snapshot);
        return snapshot;
    }

    public Snapshot get(final Long id, final User user)
    {
        return snapshotRepository.get(id);
    }

    public Snapshot require(final Long id, final User user)
    {
        Snapshot snapshot = snapshotRepository.require(id);
        return snapshot;
    }

    public Snapshot require(final Long id, final User user, final RuntimeException exception)
    {
        try
        {
            Snapshot snapshot = require(id, user);
            return snapshot;
        }
        catch (ResourceNotFoundException e)
        {
            throw exception;
        }
    }

    public Snapshot get(final Branch branch, final User user)
    {
        Snapshot snapshot = snapshotRepository.get(branch, user);
        return snapshot;
    }

    public Snapshot require(final Branch branch, final User user)
    {
        Snapshot snapshot = get(branch, user);
        Assert.notNull(snapshot, Errors.RESOURCE_NOT_FOUND);
        return snapshot;
    }

    public Snapshot patch(final Long id, final Map<String, Object> rawData, final User user)
    {
        Snapshot snapshot = this.require(id, user);
        JsonPatch.on(snapshot)
                .mappedBy(rawData)
                .fields("name")
                .apply();
        snapshotRepository.update(snapshot);
        return snapshot;
    }

    public boolean exists(final Long id, final Long branchId)
    {
        return snapshotRepository.exists(id, branchId);
    }

    public void delete(final Long id, final User user)
    {
        snapshotRepository.delete(id);
    }

    public void updateArchive(final Long snapshotId, final Archive archive, final User user)
    {
        if (CollectionUtils.isNotEmpty(archive.getModules()))
        {
            archive.getModules().forEach(module ->
            {
                if (module.getId() == null) module.setId(idGenerator.nextId());
                if (CollectionUtils.isNotEmpty(module.getApis()))
                {
                    module.getApis().stream().filter(api -> api.getId() == null)
                            .forEach(api -> api.setId(idGenerator.nextId()));
                }
            });
        }
        Snapshot snapshot = snapshotRepository.updateArchive(snapshotId, archive);
        Assert.notNull(snapshot, Errors.RESOURCE_NOT_FOUND);
    }

    public void updateHeaders(final Snapshot snapshot, final Headers headers)
    {
        snapshotRepository.updateHeaders(snapshot.getId(), headers);
    }
}

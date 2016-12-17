package cn.blinkmind.flame.server.service;

import cn.blinkmind.flame.server.bean.patch.JSONPatch;
import cn.blinkmind.flame.server.repository.ArchiveRepository;
import cn.blinkmind.flame.server.repository.BranchRepository;
import cn.blinkmind.flame.server.repository.entity.Archive;
import cn.blinkmind.flame.server.repository.entity.Branch;
import cn.blinkmind.flame.server.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ArchiveService {

    @Autowired
    private ArchiveRepository archiveRepository;

    @Autowired
    private BranchRepository branchRepository;

    public Archive get(final Branch branch, final User user) {
        Archive archive = archiveRepository.get(branch);
        return archive;
    }

    public Archive patch(final Map<String, Object> patch, final Branch branch, final User user) {
        final Archive archive = branch.getArchive();
        new JSONPatch<Archive>().source(patch).target(archive)
                .build()
                .update("description")
                .update("request");
        archive.refreshUpdatedDate();
        branchRepository.update(branch);
        return archive;
    }
}

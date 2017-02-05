package cn.blinkmind.flame.server.service;

import cn.blinkmind.flame.server.SpringBootTestNGContext;
import cn.blinkmind.flame.server.bean.Diffs;
import cn.blinkmind.flame.server.repository.entity.Archive;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ArchiveServiceTest extends SpringBootTestNGContext {

    @Autowired
    private ArchiveService archiveService;

    @Test
    public void testDiff() throws Exception {
        Archive base = new Archive();
        Archive head = new Archive();
        head.setDescription("this is head");
        Diffs<String> diffs = archiveService.diff(base, head);
        Assert.assertTrue(diffs.getModifiedCollection().contains("description"));
    }
}
package io.bayberry.core.service;

import io.bayberry.core.SpringBootTestNGContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class ArchiveServiceTest extends SpringBootTestNGContext {

    @Autowired
    private ArchiveService archiveService;

    @Test
    public void testDiff() throws Exception {
        String[] ss="a,b,c,,".split(",");
    }
}
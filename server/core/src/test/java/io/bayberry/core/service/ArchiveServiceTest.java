package io.bayberry.core.service;

import io.bayberry.core.SpringBootTestNGContext;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class ArchiveServiceTest extends SpringBootTestNGContext {

    @Autowired
    private ArchiveService archiveService;

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testDiff() throws Exception {
        String[] ss="a,b,c,,".split(",");
    }
}
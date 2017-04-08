package io.bayberry.core.domain;

import io.bayberry.core.SpringBootTestNGContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class ArchivesTest extends SpringBootTestNGContext {

    @Autowired
    private Archives archives;

    @Test
    public void testDiff() throws Exception {
        String[] ss="a,b,c,,".split(",");
    }
}
package cn.blinkmind.flame.server.repository.entity;

import java.util.List;

public class HttpArchive extends AbstractArchive implements HttpNode {
    private List<HttpModule> children;

    @Override
    public List<HttpModule> getChildren() {
        return children;
    }
}

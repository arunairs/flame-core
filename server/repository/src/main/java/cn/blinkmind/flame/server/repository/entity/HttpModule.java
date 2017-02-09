package cn.blinkmind.flame.server.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class HttpModule extends AbstractModule implements HttpNode {
    private HttpArchive archive;
    private List<HttpNode> children;

    @JsonIgnore
    @Override
    public HttpArchive getParent() {
        return archive;
    }

    @Override
    public List<HttpNode> getChildren() {
        return children;
    }
}

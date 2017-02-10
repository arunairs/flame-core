package cn.blinkmind.flame.server.repository.entity;

import java.util.List;

public class HttpModule extends AbstractModule {

    private Node parent;
    private List<HttpModule> modules;
    private List<HttpApi> apis;

    @Override
    public Node getParent() {
        return parent;
    }

    @Override
    public List<? extends Module> getModules() {
        return modules;
    }

    @Override
    public List<? extends Api> getApis() {
        return apis;
    }
}

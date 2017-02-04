package cn.blinkmind.flame.server.repository.entity;

public abstract class NodeEntity extends BasicEntity<Long> implements Resource<Long> {

    private String path;

    public abstract Resource<Long> getParent();

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

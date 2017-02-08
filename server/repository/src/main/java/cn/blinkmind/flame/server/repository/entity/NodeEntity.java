package cn.blinkmind.flame.server.repository.entity;

public abstract class NodeEntity extends BasicEntity<Long> implements Locatable {

    private String path;

    public abstract Locatable getParent();

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

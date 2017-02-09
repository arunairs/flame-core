package cn.blinkmind.flame.server.repository.entity;

public abstract class AbstractArchiveNode implements Node, Locatable {
    private String path;
    private String description;

    @Override
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

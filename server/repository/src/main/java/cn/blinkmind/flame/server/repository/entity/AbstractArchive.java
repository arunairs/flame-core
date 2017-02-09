package cn.blinkmind.flame.server.repository.entity;

public abstract class AbstractArchive extends AbstractArchiveNode implements Archive {

    @Override
    public Node getParent() {
        return null;
    }
}

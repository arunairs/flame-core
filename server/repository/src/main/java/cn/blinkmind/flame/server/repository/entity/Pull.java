package cn.blinkmind.flame.server.repository.entity;

public class Pull extends Command {
    private Snapshot snapshot;

    public Snapshot getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(Snapshot snapshot) {
        this.snapshot = snapshot;
    }
}

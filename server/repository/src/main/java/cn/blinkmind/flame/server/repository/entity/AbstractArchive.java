package cn.blinkmind.flame.server.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Transient;

public abstract class AbstractArchive extends AbstractArchiveNode implements Archive {
    @Override
    @Transient
    @JsonIgnore
    public Node getParent() {
        return null;
    }
}

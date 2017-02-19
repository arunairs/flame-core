package cn.blinkmind.flame.server.repository.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

public abstract class AbstractPersistableArchiveNameNode extends AbstractArchiveNode implements NameNode, Persistable<Long> {
    private Long id;
    private String name;

    @Id
    @Override
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

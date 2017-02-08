package cn.blinkmind.flame.server.repository.entity;

import org.springframework.data.annotation.Transient;

import java.util.List;

public class Archive extends BasicEntity<Long> implements Locatable {

    private String uri;
    private String description;
    private List<Module> modules;

    @Override
    @Transient
    public Long getId() {
        return super.getId();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    @Override
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}

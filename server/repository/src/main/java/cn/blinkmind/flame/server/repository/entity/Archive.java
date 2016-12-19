package cn.blinkmind.flame.server.repository.entity;

import cn.blinkmind.flame.server.bean.web.GeneralRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Transient;

import java.util.List;

public class Archive extends EntityBean implements Resource<Long> {
    private String description;
    private List<Module> modules;
    private GeneralRequest request;

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

    public GeneralRequest getRequest() {
        return request;
    }

    @JsonIgnoreProperties(value = {"methods"})
    public void setRequest(GeneralRequest request) {
        this.request = request;
    }

    @Override
    public void cleanup(CrudType crudType) {
        super.cleanup(crudType);
    }

    @Override
    public String getScheme() {
        return this.request != null ? this.request.getScheme() : null;
    }

    @Override
    public String getUri() {
        return this.request != null ? this.request.getUri() : null;
    }

    @JsonIgnore
    @Override
    public Resource getParent() {
        return null;
    }
}

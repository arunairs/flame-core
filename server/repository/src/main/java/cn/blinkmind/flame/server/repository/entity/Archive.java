package cn.blinkmind.flame.server.repository.entity;

import cn.blinkmind.flame.server.bean.web.GeneralRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Transient;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Archive extends EntityBean implements Resource<Long> {
    private String description;
    private List<Module> modules;
    private GeneralRequest request;
    private Set<Long> moduleIds;
    private Set<Long> apiIds;

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

    @JsonIgnore
    public Set<Long> getModuleIds() {
        return moduleIds;
    }

    private void setModuleIds(Set<Long> moduleIds) {
        this.moduleIds = moduleIds;
    }

    @JsonIgnore
    public Set<Long> getApiIds() {
        return apiIds;
    }

    private void setApiIds(Set<Long> apiIds) {
        this.apiIds = apiIds;
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

    @JsonIgnore
    @Override
    public Set<Long> getChildrenId() {
        return this.modules == null || this.modules.size() < 1 ?
                null : this.modules.stream().filter(module -> module.getId() != null).map(Module::getId).collect(Collectors.toCollection(HashSet<Long>::new));
    }

    public void refresh() {
        refreshModuleIds();
        refreshApiIds();
    }

    private void refreshModuleIds() {
        this.moduleIds = this.getChildrenId();
    }

    private void refreshApiIds() {
        this.modules.stream().filter(module -> module.getApis() != null && module.getApis().size() > 0)
                .forEach(module -> this.apiIds.addAll(module.getChildrenId()));
    }
}

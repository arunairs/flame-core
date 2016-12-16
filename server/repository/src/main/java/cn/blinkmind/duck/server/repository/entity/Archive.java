package cn.blinkmind.duck.server.repository.entity;

import cn.blinkmind.duck.server.bean.web.GeneralRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Transient;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Archive extends EntityBean implements Resource {
    private String description;
    private List<Module> modules;
    private GeneralRequest request;
    private Set<Long> moduleIdSet;
    private Set<Long> apiIdSet;

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
    public Set<Long> getModuleIdSet() {
        return moduleIdSet;
    }

    public void setModuleIdSet(Set<Long> moduleIdSet) {
        this.moduleIdSet = moduleIdSet;
    }

    public void refreshIdSet() {
        if (this.modules == null || this.modules.size() < 1) this.moduleIdSet = null;
        if (this.moduleIdSet == null) this.moduleIdSet = new HashSet<>();
        else this.moduleIdSet.clear();
        for (Module module : this.modules) {
            this.moduleIdSet.add(module.getId());
            this.refreshApiIdSet(module);
        }
    }

    private void refreshApiIdSet(Module module) {
        if (module.getApis() == null || module.getApis().size() < 1) this.apiIdSet = null;
        if (this.apiIdSet == null) this.apiIdSet = new HashSet<>();
        else this.apiIdSet.clear();
        for (Api api : module.getApis())
            this.apiIdSet.add(api.getId());
    }

    @JsonIgnore
    public Set<Long> getApiIdSet() {
        return apiIdSet;
    }

    public void setApiIdSet(Set<Long> apiIdSet) {
        this.apiIdSet = apiIdSet;
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

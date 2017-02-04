package cn.blinkmind.flame.server.repository.entity;

import cn.blinkmind.flame.server.web.request.http.BasicHttpRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Transient;

import java.util.List;

public class Archive extends BasicEntity<Long> implements Resource<Long> {

    private String description;
    private List<Module> modules;
    private BasicHttpRequest request = new BasicHttpRequest();

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

    public BasicHttpRequest getRequest() {
        return request;
    }

    @JsonIgnoreProperties(value = {"methods"})
    private void setRequest(BasicHttpRequest request) {
        this.request = request;
    }

    @Override
    public String getScheme() {
        return this.request != null ? this.request.getScheme() : null;
    }

    @Override
    public String getUri() {
        return this.request != null ? this.request.getUri() : null;
    }
}

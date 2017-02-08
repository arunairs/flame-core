package cn.blinkmind.flame.server.repository.entity;

import cn.blinkmind.flame.server.web.request.http.HttpRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.util.StringJoiner;

public class Api extends NodeEntity implements Resource {

    private String name;
    private String description;
    private HttpRequest request = new HttpRequest();
    private Module module;

    @Id
    @Override
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    public Long getId() {
        return super.getId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HttpRequest getRequest() {
        return request;
    }

    private void setRequest(HttpRequest request) {
        this.request = request;
    }

    @Transient
    @JsonIgnore
    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public String getUrl() {
        String scheme = getScheme();
        String uri = getUri();
        String url = scheme == null ? null : scheme + "://";
        if (uri != null)
            url += uri;
        return url;
    }

    @JsonIgnore
    @Override
    public String getScheme() {
        if (this.request != null && StringUtils.isNotBlank(this.request.getScheme())) {
            return this.request.getScheme();
        }
        return null;
    }

    @JsonIgnore
    @Override
    public String getUri() {
        StringJoiner joiner = new StringJoiner("/");
        if (getParent() != null) {
            joiner.add(getParent().getUri());
        }
        if (this.request != null && StringUtils.isNotBlank(this.request.getUri())) {
            joiner.add(this.request.getUri());
        }
        return joiner.toString();
    }

    @JsonIgnore
    @Override
    public Locatable getParent() {
        return module;
    }
}

package cn.blinkmind.flame.server.repository.entity;

import cn.blinkmind.flame.server.web.request.http.BasicHttpRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Module extends BasicEntity<Long> implements Resource<Long> {

    private String name;
    private BasicHttpRequest request = new BasicHttpRequest();
    private List<Api> apis;
    private Archive archive;

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

    public BasicHttpRequest getRequest() {
        return request;
    }

    @JsonIgnoreProperties(value = {"methods"})
    private void setRequest(BasicHttpRequest request) {
        this.request = request;
    }

    public List<Api> getApis() {
        return apis;
    }

    public void setApis(List<Api> apis) {
        this.apis = apis;
    }

    @Transient
    @JsonIgnore
    public Archive getArchive() {
        return archive;
    }

    public void setArchive(Archive archive) {
        this.archive = archive;
    }

    public void addApi(Api api) {
        if (apis == null) apis = new ArrayList<>();
        apis.add(api);
        api.setModule(this);
    }

    @Override
    public String getScheme() {
        if (this.request != null && StringUtils.isNotBlank(this.request.getScheme())) {
            return this.request.getScheme();
        }
        if (getParent() != null && StringUtils.isNotBlank(getParent().getScheme())) {
            return getParent().getScheme();
        }
        return null;
    }

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
    public Resource getParent() {
        return archive;
    }
}

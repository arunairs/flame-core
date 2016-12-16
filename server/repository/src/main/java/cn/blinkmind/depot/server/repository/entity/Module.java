package cn.blinkmind.depot.server.repository.entity;

import cn.blinkmind.depot.server.bean.web.GeneralRequest;
import cn.blinkmind.depot.server.repository.util.UrlStringBuilder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.util.ArrayList;
import java.util.List;

public class Module extends EntityBean implements Resource {

    private String name;
    private GeneralRequest request;
    private List<Api> apis;
    private Archive archive;
    private int ordinal;

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

    public GeneralRequest getRequest() {
        return request;
    }

    @JsonIgnoreProperties(value = {"methods"})
    public void setRequest(GeneralRequest request) {
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

    public int getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(int ordinal) {
        this.ordinal = ordinal;
    }

    public void addApi(Api api) {
        if (apis == null) apis = new ArrayList<>();
        apis.add(api);
        api.setModule(this);
    }

    @Override
    public void cleanup(CrudType crudType) {
        super.cleanup(crudType);
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
        UrlStringBuilder stringBuilder = new UrlStringBuilder();
        if (getParent() != null) {
            stringBuilder.append(getParent().getUri());
        }
        if (this.request != null && StringUtils.isNotBlank(this.request.getUri())) {
            stringBuilder.append(this.request.getUri());
        }
        return stringBuilder.toString();
    }

    @JsonIgnore
    @Override
    public Resource getParent() {
        return archive;
    }
}

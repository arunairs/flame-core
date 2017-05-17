package io.bayberry.core.repository.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.bayberry.common.api.Request;
import io.bayberry.common.api.Response;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Transient;

@Getter
@Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type", visible = true)
@JsonSubTypes({@JsonSubTypes.Type(value = HttpApi.class)})
public abstract class Api extends BaseEntity {

    @Transient
    private Long branchId;
    private Long moduleId;
    private String name;
    private String description;
    private ApiType type;

    public abstract Request getRequest();

    public abstract Response getResponse();
}

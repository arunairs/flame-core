package io.bayberry.repository.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.bayberry.common.protocol.Protocol;
import io.bayberry.common.protocol.Request;
import io.bayberry.common.protocol.Response;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Transient;

@Getter
@Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "protocol")
@JsonSubTypes({@JsonSubTypes.Type(value = HttpApi.class)})
public abstract class Api extends BaseEntity {

    private String name;
    private String description;
    private Long moduleId;
    @Transient
    private Long branchId;

    public abstract Protocol getProtocol();

    public abstract Request getRequest();

    public abstract Response getResponse();
}

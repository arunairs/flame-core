package io.bayberry.repository.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.bayberry.common.protocol.Request;
import io.bayberry.common.protocol.Response;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "protocol")
@JsonSubTypes({@JsonSubTypes.Type(value = HttpInterface.class)})
public abstract class Interface extends BaseModel {

    private String name;
    private String description;
    private String protocol;
    private Long parentId;
    private Request request;
    private Response response;
}

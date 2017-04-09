package io.bayberry.repository.entity;

import io.bayberry.common.protocol.Request;
import io.bayberry.common.protocol.Response;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@ToString(callSuper = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "protocol")
@JsonSubTypes({@JsonSubTypes.Type(value = HttpApi.class)})
public abstract class Api extends Node {

    public abstract String getProtocol();

    public abstract Request getRequest();

    public abstract Response getResponse();
}

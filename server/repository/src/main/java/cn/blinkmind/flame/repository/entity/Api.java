package cn.blinkmind.flame.repository.entity;

import cn.blinkmind.flame.common.web.Request;
import cn.blinkmind.flame.common.web.Response;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "protocol")
@JsonSubTypes({@JsonSubTypes.Type(value = HttpApi.class)})
public interface Api extends ArchiveNode, NameNode, Persistable<Long> {
    Request getRequest();

    Response getResponse();
}

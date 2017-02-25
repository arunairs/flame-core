package cn.blinkmind.flame.repository.entity;

import cn.blinkmind.flame.common.protocol.Response;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@Getter
@Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "protocol")
@JsonSubTypes({@JsonSubTypes.Type(value = HttpApi.class)})
public abstract class AbstractApi extends AbstractArchiveNode {
    public abstract String getProtocol();

    public abstract Response getResponse();

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}

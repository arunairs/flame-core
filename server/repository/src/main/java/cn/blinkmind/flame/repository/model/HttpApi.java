package cn.blinkmind.flame.repository.model;

import cn.blinkmind.flame.common.protocol.Protocol;
import cn.blinkmind.flame.common.protocol.http.HttpRequest;
import cn.blinkmind.flame.common.protocol.http.HttpResponse;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;


@Getter
@Setter
@JsonTypeName(value = Protocol.Values.HTTP)
public class HttpApi extends AbstractApi {
    private HttpRequest request;
    private HttpResponse response;

    @Override
    public String getProtocol() {
        return Protocol.Values.HTTP;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}

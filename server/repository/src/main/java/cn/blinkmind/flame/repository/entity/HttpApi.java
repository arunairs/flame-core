package cn.blinkmind.flame.repository.entity;

import cn.blinkmind.flame.common.web.http.HttpRequest;
import cn.blinkmind.flame.common.web.http.HttpResponse;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@Getter
@Setter
@JsonTypeName(value = Protocol.Names.HTTP)
public class HttpApi extends AbstractApi {
    private HttpRequest request;
    private HttpResponse response;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}

package io.bayberry.repository.model;

import io.bayberry.common.protocol.Protocol;
import io.bayberry.common.protocol.http.HttpRequest;
import io.bayberry.common.protocol.http.HttpResponse;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@JsonTypeName(value = Protocol.Values.HTTP)
public class HttpApi extends AbstractApi {
    private HttpRequest request;
    private HttpResponse response;

    @Override
    public String getProtocol() {
        return Protocol.Values.HTTP;
    }
}

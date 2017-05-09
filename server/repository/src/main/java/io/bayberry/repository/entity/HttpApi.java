package io.bayberry.repository.entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import io.bayberry.common.protocol.Protocol;
import io.bayberry.common.protocol.http.HttpRequest;
import io.bayberry.common.protocol.http.HttpResponse;
import io.bayberry.repository.annotation.ProtocolType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ProtocolType(Protocol.HTTP)
@JsonTypeName(value = Protocol.Names.HTTP)
public class HttpApi extends Api {

    private HttpRequest request;
    private HttpResponse response;

    @Override
    public Protocol getProtocol() {
        return Protocol.HTTP;
    }
}

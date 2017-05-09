package io.bayberry.core.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import io.bayberry.common.protocol.Protocol;
import io.bayberry.common.protocol.http.HttpRequest;
import io.bayberry.common.protocol.http.HttpResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonTypeName(value = Protocol.Names.HTTP)
public class HttpApiRequest extends ApiRequest {

    private HttpRequest request;
    private HttpResponse response;

    @Override
    public Protocol getProtocol() {
        return Protocol.HTTP;
    }
}

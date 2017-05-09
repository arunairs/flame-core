package io.bayberry.core.dto;

import io.bayberry.common.protocol.Protocol;
import io.bayberry.repository.annotation.ProtocolType;
import io.bayberry.repository.entity.Api;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ProtocolType(Protocol.HTTP)
public class HttpApiResponse extends ApiResponse {

    public HttpApiResponse(Api api) {
        super(api);
    }

    @Override
    public Protocol getProtocol() {
        return Protocol.HTTP;
    }
}

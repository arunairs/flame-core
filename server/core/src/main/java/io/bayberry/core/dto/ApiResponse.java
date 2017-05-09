package io.bayberry.core.dto;

import io.bayberry.common.protocol.Protocol;
import io.bayberry.common.protocol.Request;
import io.bayberry.common.protocol.Response;
import io.bayberry.common.util.BeanUtils;
import io.bayberry.repository.entity.Api;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ApiResponse {

    private Long id;
    private String name;
    private String description;
    private Long moduleId;
    private Request request;
    private Response response;

    public abstract Protocol getProtocol();

    public ApiResponse(Api api) {
        BeanUtils.copyProperties(api, this);
    }
}

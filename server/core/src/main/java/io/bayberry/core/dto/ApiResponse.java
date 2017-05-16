package io.bayberry.core.dto;

import io.bayberry.common.api.Request;
import io.bayberry.common.api.Response;
import io.bayberry.repository.entity.Api;
import io.bayberry.repository.entity.ApiType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ApiResponse extends AbstractEntityResponse<Api> {

    private Long moduleId;
    private String name;
    private String description;
    private ApiType type;
    private Request request;
    private Response response;

    public ApiResponse(Api source) {
        super(source);
    }
}

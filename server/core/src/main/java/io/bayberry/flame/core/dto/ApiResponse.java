package io.bayberry.flame.core.dto;

import io.bayberry.flame.common.api.Request;
import io.bayberry.flame.common.api.Response;
import io.bayberry.flame.core.repository.entity.Api;
import io.bayberry.flame.core.repository.entity.ApiType;
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

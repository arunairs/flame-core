package io.bayberry.core.dto;

import io.bayberry.core.annotation.ApiInfo;
import io.bayberry.core.repository.entity.Api;
import io.bayberry.core.repository.entity.ApiType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiInfo(type = ApiType.HTTP)
public class HttpApiResponse extends ApiResponse {

    public HttpApiResponse(Api api) {
        super(api);
    }
}

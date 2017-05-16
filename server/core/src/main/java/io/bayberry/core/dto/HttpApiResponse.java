package io.bayberry.core.dto;

import io.bayberry.repository.annotation.ApiInfo;
import io.bayberry.repository.entity.Api;
import io.bayberry.repository.entity.ApiType;
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

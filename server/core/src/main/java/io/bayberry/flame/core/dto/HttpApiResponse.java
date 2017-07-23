package io.bayberry.flame.core.dto;

import io.bayberry.flame.core.annotation.ApiInfo;
import io.bayberry.flame.core.repository.entity.Api;
import io.bayberry.flame.core.repository.entity.ApiType;
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

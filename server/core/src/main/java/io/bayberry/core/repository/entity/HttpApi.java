package io.bayberry.core.repository.entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import io.bayberry.common.api.http.HttpRequest;
import io.bayberry.common.api.http.HttpResponse;
import io.bayberry.core.annotation.ApiInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiInfo(type = ApiType.HTTP)
@JsonTypeName(value = "HTTP")
public class HttpApi extends Api {

    private HttpRequest request;
    private HttpResponse response;
}

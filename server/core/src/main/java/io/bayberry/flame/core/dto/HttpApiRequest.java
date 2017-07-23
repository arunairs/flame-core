package io.bayberry.flame.core.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import io.bayberry.flame.common.api.http.HttpRequest;
import io.bayberry.flame.common.api.http.HttpResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonTypeName(value = "HTTP")
public class HttpApiRequest extends ApiRequest {

    private HttpRequest request;
    private HttpResponse response;
}

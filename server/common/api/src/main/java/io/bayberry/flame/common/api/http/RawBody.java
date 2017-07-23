package io.bayberry.flame.common.api.http;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonTypeName(value = HttpRequestBodyType.Names.RAW)
public class RawBody extends HttpRequestBody {

    private String data;
    private String comment;

    @Override
    public HttpRequestBodyType getType() {
        return HttpRequestBodyType.RAW;
    }
}

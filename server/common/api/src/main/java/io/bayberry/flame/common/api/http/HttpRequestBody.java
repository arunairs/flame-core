package io.bayberry.flame.common.api.http;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = FormDataBody.class),
        @JsonSubTypes.Type(value = UrlEncodedFormBody.class),
        @JsonSubTypes.Type(value = RawBody.class)
})
public abstract class HttpRequestBody<T> {

    public abstract HttpRequestBodyType getType();

    public abstract T getData();
}

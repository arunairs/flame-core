package io.bayberry.common.api.http;

import io.bayberry.common.api.Request;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedHashSet;

@Getter
@Setter
@ToString
public class HttpRequest implements Request {

    private String uri;
    private String scheme;
    private LinkedHashSet<HttpRequestMethod> methods;
    private LinkedHashSet<HttpRequestParameter> headers;
    private LinkedHashSet<HttpRequestParameter> pathParams;
    private LinkedHashSet<HttpRequestParameter> queryParams;
    private LinkedHashSet<HttpRequestParameter> cookies;
    private HttpRequestBody body;
}

package io.bayberry.common.protocol.http;

import io.bayberry.common.protocol.Request;
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
    private LinkedHashSet<SingleFieldHttpRequestParameter> headers;
    private LinkedHashSet<SingleFieldHttpRequestParameter> paths;
    private LinkedHashSet<SingleFieldHttpRequestParameter> queries;
    private LinkedHashSet<SingleFieldHttpRequestParameter> cookies;
    private HttpRequestBody body;
}

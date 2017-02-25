package cn.blinkmind.flame.common.protocol.http;

import cn.blinkmind.flame.common.protocol.Request;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.LinkedHashSet;

@Getter
@Setter
public class HttpRequest implements Request {
    private String uri;
    private String scheme;
    private LinkedHashSet<HttpRequestMethod> methods;
    private LinkedHashSet<SingleFieldHttpRequestParameter> headers;
    private LinkedHashSet<SingleFieldHttpRequestParameter> paths;
    private LinkedHashSet<SingleFieldHttpRequestParameter> queries;
    private LinkedHashSet<SingleFieldHttpRequestParameter> cookies;
    private HttpRequestBody body;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}

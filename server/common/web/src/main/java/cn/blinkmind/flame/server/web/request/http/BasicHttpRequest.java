package cn.blinkmind.flame.server.web.request.http;

import java.util.LinkedHashSet;

public class BasicHttpRequest {

    private String uri;
    private String scheme;
    private LinkedHashSet<HttpRequestMethod> methods;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public LinkedHashSet<HttpRequestMethod> getMethods() {
        return methods;
    }

    public void setMethods(LinkedHashSet<HttpRequestMethod> methods) {
        this.methods = methods;
    }
}

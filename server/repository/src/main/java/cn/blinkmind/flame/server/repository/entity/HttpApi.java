package cn.blinkmind.flame.server.repository.entity;

import cn.blinkmind.flame.server.web.request.http.HttpRequest;
import cn.blinkmind.flame.server.web.request.http.HttpResponse;
import org.springframework.data.annotation.Transient;

public class HttpApi extends AbstractApi{
    private HttpRequest request;
    private HttpResponse response;
    private HttpModule module;

    @Override
    public HttpRequest getRequest() {
        return request;
    }

    public void setRequest(HttpRequest request) {
        this.request = request;
    }

    @Override
    public HttpResponse getResponse() {
        return response;
    }

    public void setResponse(HttpResponse response) {
        this.response = response;
    }

    @Transient
    public HttpModule getModule() {
        return module;
    }

    public void setModule(HttpModule module) {
        this.module = module;
    }

    @Override
    public HttpModule getParent() {
        return module;
    }
}

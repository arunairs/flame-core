package cn.blinkmind.flame.server.web.request.http;

import java.util.LinkedHashSet;

public class HttpRequestBody
{
    private LinkedHashSet<SingleFieldHttpRequestParameter> parameters;
    private String text;

    public LinkedHashSet<SingleFieldHttpRequestParameter> getParameters()
    {
        return parameters;
    }

    public void setParameters(LinkedHashSet<SingleFieldHttpRequestParameter> parameters)
    {
        this.text = null;
        this.parameters = parameters;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.parameters.clear();
        this.text = text;
    }
}

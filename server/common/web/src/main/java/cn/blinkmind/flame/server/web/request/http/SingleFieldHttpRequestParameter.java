package cn.blinkmind.flame.server.web.request.http;

public class SingleFieldHttpRequestParameter implements HttpRequestParameter {

    private String name;
    private String value;
    private Boolean isRequired;
    private HttpParameterType httpParameterType;
    private String comment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getRequired() {
        return isRequired;
    }

    public void setRequired(Boolean required) {
        isRequired = required;
    }

    public HttpParameterType getHttpParameterType() {
        return httpParameterType;
    }

    public void setHttpParameterType(HttpParameterType httpParameterType) {
        this.httpParameterType = httpParameterType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

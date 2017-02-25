package cn.blinkmind.flame.common.protocol.http;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@Getter
@Setter
public class SingleFieldHttpRequestParameter implements HttpRequestParameter {
    private String name;
    private String value;
    private Boolean isRequired;
    private HttpParameterType httpParameterType;
    private String comment;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}

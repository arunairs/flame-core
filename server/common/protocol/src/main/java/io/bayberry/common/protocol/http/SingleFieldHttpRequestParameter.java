package cn.blinkmind.flame.common.protocol.http;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SingleFieldHttpRequestParameter implements HttpRequestParameter {
    private String name;
    private String value;
    private Boolean isRequired;
    private HttpParameterType httpParameterType;
    private String comment;
}

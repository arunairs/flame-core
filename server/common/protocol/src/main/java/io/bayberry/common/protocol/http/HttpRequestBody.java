package io.bayberry.common.protocol.http;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;

@Getter
@Setter
public class HttpRequestBody {

    private LinkedHashSet<SingleFieldHttpRequestParameter> parameters;
    private String text;
}

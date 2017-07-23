package io.bayberry.flame.common.api.http;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum HttpRequestBodyType {

    FORM_DATA(Names.FORM_DATA),
    URL_ENCODED_FORM(Names.URL_ENCODED_FORM),
    RAW(Names.RAW);

    @Getter
    private final String name;

    public interface Names {
        String FORM_DATA = "FORM_DATA";
        String URL_ENCODED_FORM = "URL_ENCODED_FORM";
        String RAW = "RAW";
    }
}

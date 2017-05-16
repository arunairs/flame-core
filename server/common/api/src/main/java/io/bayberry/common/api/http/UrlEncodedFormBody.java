package io.bayberry.common.api.http;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;

@Getter
@Setter
@JsonTypeName(value = HttpRequestBodyType.Names.URL_ENCODED_FORM)
public class UrlEncodedFormBody extends HttpRequestBody<LinkedHashSet<HttpRequestParameter>> {

    private LinkedHashSet<HttpRequestParameter> data = Sets.newLinkedHashSet();

    @Override
    public HttpRequestBodyType getType() {
        return HttpRequestBodyType.URL_ENCODED_FORM;
    }
}

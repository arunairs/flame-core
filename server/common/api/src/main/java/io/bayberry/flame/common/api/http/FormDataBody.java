package io.bayberry.flame.common.api.http;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;

@Getter
@Setter
@JsonTypeName(value = HttpRequestBodyType.Names.FORM_DATA)
public class FormDataBody extends HttpRequestBody<LinkedHashSet<FormDataRequestParameter>> {

    private LinkedHashSet<FormDataRequestParameter> data = Sets.newLinkedHashSet();

    @Override
    public HttpRequestBodyType getType() {
        return HttpRequestBodyType.FORM_DATA;
    }
}

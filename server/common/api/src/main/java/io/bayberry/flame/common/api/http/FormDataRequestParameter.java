package io.bayberry.flame.common.api.http;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormDataRequestParameter extends HttpRequestParameter {

    private Boolean isFile;
}

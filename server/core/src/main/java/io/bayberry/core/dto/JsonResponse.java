package io.bayberry.core.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JsonResponse {

    private Integer code;
    private String message;
    private Object data;
}

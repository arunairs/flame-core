package io.bayberry.flame.core.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Error {

    DOCUMENT_NOT_FOUND(10000, "document not dound"),
    DOCUMENT_NAME_IS_BLANK(1000, ""),

    BRANCH_NOT_FOUND(10000, "branch not dound"),
    BRANCH_ALREADY_EXISTS(1000, ""),
    BRANCH_ORIGIN_IS_NOT_FOUND(1000, ""),
    BRANCH_NAME_IS_BLANK(1000, ""),

    MODULE_NOT_FOUND(1000, ""),
    MODULE_NAME_IS_BLANK(1000, ""),

    API_NOT_FOUND(1000, ""),
    API_NAME_IS_BLANK(1000, ""),

    RESOURCE_ALREADY_EXISTS(1000, "");

    private Integer code;
    private String message;
}

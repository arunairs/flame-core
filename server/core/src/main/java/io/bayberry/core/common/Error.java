package io.bayberry.core.common;

public enum Error {

    DOCUMENT_NOT_FOUND(10000, "document not dound"),
    DOCUMENT_NAME_IS_BLANK(1000, ""),

    BRANCH_NOT_FOUND(10000, "branch not dound"),
    BRANCH_ALREADY_EXISTS(1000, ""),
    BRANCH_ORIGIN_IS_NOT_FOUND(1000, ""),
    BRANCH_NAME_IS_BLANK(1000, ""),

    MODULE_NOT_FOUND(1000, ""),
    MODULE_NAME_IS_BLANK(1000, ""),

    RESOURCE_ALREADY_EXISTS(1000, "");

    private String message;
    private Integer code;

    Error(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}

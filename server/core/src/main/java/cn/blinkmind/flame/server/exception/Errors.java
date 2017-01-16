package cn.blinkmind.flame.server.exception;

import org.springframework.http.HttpStatus;

public final class Errors
{
    public static final Error DOCUMENT_IS_NOT_FOUND = new Error(HttpStatus.NOT_FOUND, 40410001, "document is not found");
    public static final Error DOCUMENT_NAME_IS_BLANK = new Error(HttpStatus.BAD_REQUEST, 40010001, "document name is blank");

    public static final Error BRANCH_IS_NOT_FOUND = new Error(HttpStatus.NOT_FOUND, 40410001, "branch is not found");
    public static final Error BRANCH_DOCUMENT_IS_NOT_SPECIFIED = new Error(HttpStatus.BAD_REQUEST, 40010001, "document is not specified");
    public static final Error BRANCH_NAME_IS_BLANK = new Error(HttpStatus.BAD_REQUEST, 40010001, "branch name is blank");
    public static final Error BRANCH_ORIGIN_IS_NOT_FOUND = new Error(HttpStatus.BAD_REQUEST, 40010001, "branch name is blank");

    public static final Error SNAPSHOT_BRANCH_IS_NOT_SPECIFIED = new Error(HttpStatus.BAD_REQUEST, 40010001, "branch is not specified");
    public static final Error SNAPSHOT_NAME_IS_BLANK = new Error(HttpStatus.BAD_REQUEST, 40010001, "snapshot name is blank");

    public static final Error ARCHIVE_STATUS_IS_NULL = new Error(HttpStatus.BAD_REQUEST, 40011001, "archive status is null");
    public static final Error ARCHIVE_VERSION_IS_NULL = new Error(HttpStatus.BAD_REQUEST, 40011001, "archive version is null");
    public static final Error ARCHIVE_VERSION_IS_INVALID = new Error(HttpStatus.BAD_REQUEST, 40011002, "archive version is invalid");
    public static final Error ARCHIVE_BRANCH_IS_NOT_SPECIFIED = new Error(HttpStatus.BAD_REQUEST, 40011004, "branch is not specified");
    public static final Error ARCHIVE_DOCUMENT_IS_NOT_SPECIFIED = new Error(HttpStatus.BAD_REQUEST, 40011004, "document is not specified");
    public static final Error ARCHIVE_IS_ALREADY_RELEASED = new Error(HttpStatus.BAD_REQUEST, 40011005, "can not modify the archive since it is already released");

    public static final Error MODULE_NAME_IS_BLANK = new Error(HttpStatus.BAD_REQUEST, 40012001, "module name is blank");

    public static final Error API_MODULE_IS_NOT_SPECIFIED = new Error(HttpStatus.BAD_REQUEST, 40013001, "module is not specified");
    public static final Error API_REQUEST_IS_NULL = new Error(HttpStatus.BAD_REQUEST, 40013002, "request is null");

    public static final Error REQUEST_URL_IS_BLANK = new Error(HttpStatus.BAD_REQUEST, 40014001, "request url is blank");
    public static final Error REQUEST_URL_IS_INVALID = new Error(HttpStatus.BAD_REQUEST, 40014002, "request url is invalid");
    public static final Error REQUEST_SCHEME_IS_BLANK = new Error(HttpStatus.BAD_REQUEST, 40014003, "request scheme is invalid");
    public static final Error REQUEST_URI_IS_BLANK = new Error(HttpStatus.BAD_REQUEST, 40014004, "request uri is invalid");

    public static final Error ACCOUNT_NAME_IS_BLANK = new Error(HttpStatus.BAD_REQUEST, 4004001, "account name is blank");
    public static final Error ACCOUNT_PASSWORD_IS_BLANK = new Error(HttpStatus.BAD_REQUEST, 4004002, "password is blank");

    public static final Error RESOURCE_NOT_FOUND = new Error(HttpStatus.NOT_FOUND, 40400001, "resource is not found");
    public static final Error RESOURCE_ALREADY_EXISTS = new Error(HttpStatus.CONFLICT, 40900001, "resource already exists");
}

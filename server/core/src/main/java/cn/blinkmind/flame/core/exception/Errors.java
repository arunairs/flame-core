package cn.blinkmind.flame.core.exception;

import org.springframework.http.HttpStatus;

public final class Errors
{
    public static final BadRequestException DOCUMENT_IS_NOT_FOUND = new BadRequestException(HttpStatus.NOT_FOUND, 40410001, "document is not found");
    public static final BadRequestException DOCUMENT_NAME_IS_BLANK = new BadRequestException(HttpStatus.BAD_REQUEST, 40010001, "document name is blank");

    public static final BadRequestException BRANCH_IS_NOT_FOUND = new BadRequestException(HttpStatus.NOT_FOUND, 40410001, "branch is not found");
    public static final BadRequestException BRANCH_DOCUMENT_IS_NOT_SPECIFIED = new BadRequestException(HttpStatus.BAD_REQUEST, 40010001, "document is not specified");
    public static final BadRequestException BRANCH_NAME_IS_BLANK = new BadRequestException(HttpStatus.BAD_REQUEST, 40010001, "branch name is blank");
    public static final BadRequestException BRANCH_ORIGIN_IS_NOT_FOUND = new BadRequestException(HttpStatus.BAD_REQUEST, 40010001, "branch name is blank");
    public static final BadRequestException BRANCH_NOT_MATCHES_DOCUMENT = new BadRequestException(HttpStatus.BAD_REQUEST, 40010001, "snapshot name is blank");

    public static final BadRequestException SNAPSHOT_IS_NOT_SPECIFIED = new BadRequestException(HttpStatus.BAD_REQUEST, 4004002, "password is blank");
    public static final BadRequestException SNAPSHOT_IS_NOT_FOUND = new BadRequestException(HttpStatus.NOT_FOUND, 40410001, "branch is not found");
    public static final BadRequestException SNAPSHOT_BRANCH_IS_NOT_SPECIFIED = new BadRequestException(HttpStatus.BAD_REQUEST, 40010001, "branch is not specified");
    public static final BadRequestException SNAPSHOT_NAME_IS_BLANK = new BadRequestException(HttpStatus.BAD_REQUEST, 40010001, "snapshot name is blank");
    public static final BadRequestException SNAPSHOT_NOT_MATCHES_BRANCH = new BadRequestException(HttpStatus.BAD_REQUEST, 40010001, "snapshot name is blank");
    public static final BadRequestException SNAPSHOT_IS_OUTDATED = new BadRequestException(HttpStatus.BAD_REQUEST, 40011001, "snapshot is out of date");

    public static final BadRequestException ARCHIVE_VERSION_IS_NULL = new BadRequestException(HttpStatus.BAD_REQUEST, 40011001, "archive version is null");
    public static final BadRequestException ARCHIVE_VERSION_IS_INVALID = new BadRequestException(HttpStatus.BAD_REQUEST, 40011002, "archive version is invalid");
    public static final BadRequestException ARCHIVE_BRANCH_IS_NOT_SPECIFIED = new BadRequestException(HttpStatus.BAD_REQUEST, 40011004, "branch is not specified");
    public static final BadRequestException ARCHIVE_DOCUMENT_IS_NOT_SPECIFIED = new BadRequestException(HttpStatus.BAD_REQUEST, 40011004, "document is not specified");
    public static final BadRequestException ARCHIVE_IS_ALREADY_RELEASED = new BadRequestException(HttpStatus.BAD_REQUEST, 40011005, "can not modify the archive since it is already released");

    public static final BadRequestException MODULE_NAME_IS_BLANK = new BadRequestException(HttpStatus.BAD_REQUEST, 40012001, "module name is blank");

    public static final BadRequestException API_MODULE_IS_NOT_SPECIFIED = new BadRequestException(HttpStatus.BAD_REQUEST, 40013001, "module is not specified");
    public static final BadRequestException API_REQUEST_IS_NULL = new BadRequestException(HttpStatus.BAD_REQUEST, 40013002, "request is null");

    public static final BadRequestException REQUEST_URL_IS_BLANK = new BadRequestException(HttpStatus.BAD_REQUEST, 40014001, "request url is blank");
    public static final BadRequestException REQUEST_URL_IS_INVALID = new BadRequestException(HttpStatus.BAD_REQUEST, 40014002, "request url is invalid");
    public static final BadRequestException REQUEST_SCHEME_IS_BLANK = new BadRequestException(HttpStatus.BAD_REQUEST, 40014003, "request scheme is invalid");
    public static final BadRequestException REQUEST_URI_IS_BLANK = new BadRequestException(HttpStatus.BAD_REQUEST, 40014004, "request uri is invalid");

    public static final BadRequestException ACCOUNT_NAME_IS_BLANK = new BadRequestException(HttpStatus.BAD_REQUEST, 4004001, "account name is blank");
    public static final BadRequestException ACCOUNT_PASSWORD_IS_BLANK = new BadRequestException(HttpStatus.BAD_REQUEST, 4004002, "password is blank");

    public static final BadRequestException RESOURCE_NOT_FOUND = new BadRequestException(HttpStatus.NOT_FOUND, 40400001, "resource is not found");
    public static final BadRequestException RESOURCE_ALREADY_EXISTS = new BadRequestException(HttpStatus.CONFLICT, 40900001, "resource already exists");
}

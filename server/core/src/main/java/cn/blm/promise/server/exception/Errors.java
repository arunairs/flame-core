package cn.blm.promise.server.exception;

import org.springframework.http.HttpStatus;

/**
 * @author jiaan.zhang@oracle.com
 * @date 05/10/2016 10:27 PM
 */
public class Errors
{
	public static final ErrorInfo DOCUMENT_NAME_IS_BLANK = new ErrorInfo(HttpStatus.BAD_REQUEST, 4001001, "document name is blank");
	public static final ErrorInfo ACCOUNT_NAME_IS_BLANK = new ErrorInfo(HttpStatus.BAD_REQUEST, 4002001, "name is blank");
	public static final ErrorInfo ACCOUNT_PASSWORD_IS_BLANK = new ErrorInfo(HttpStatus.BAD_REQUEST, 4002002, "password is blank");
	public static final ErrorInfo RESOURCE_ALREADY_EXISTS = new ErrorInfo(HttpStatus.CONFLICT, 4090001, "this resource already exists");
}

package cn.blm.promise.server.exception;

import org.springframework.http.HttpStatus;

/**
 * @author jiaan.zhang@oracle.com
 * @date 05/10/2016 5:29 PM
 */
public class BadRequestException extends RuntimeException
{
	private HttpStatus httpStatus;
	private ErrorInfo errorInfo;

	public BadRequestException(String message, Integer code)
	{
		this(HttpStatus.BAD_REQUEST, code, null);
	}

	public BadRequestException(ErrorInfo errorInfo)
	{
		this(HttpStatus.BAD_REQUEST, errorInfo);
	}

	public BadRequestException(HttpStatus httpStatus, String message)
	{
		this(httpStatus, null, message);
	}

	public BadRequestException(HttpStatus httpStatus, Integer code, String message)
	{
		this(httpStatus, new ErrorInfo(code, message));
	}

	public BadRequestException(HttpStatus httpStatus, ErrorInfo errorInfo)
	{
		this.httpStatus = httpStatus;
		this.errorInfo = errorInfo;
	}

	public HttpStatus getHttpStatus()
	{
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus)
	{
		this.httpStatus = httpStatus;
	}

	public ErrorInfo getErrorInfo()
	{
		return errorInfo;
	}

	public void setErrorInfo(ErrorInfo errorInfo)
	{
		this.errorInfo = errorInfo;
	}
}

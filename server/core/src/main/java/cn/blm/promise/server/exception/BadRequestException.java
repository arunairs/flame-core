package cn.blm.promise.server.exception;

import org.springframework.http.HttpStatus;

/**
 * @author jiaan.zhang@oracle.com
 * @date 05/10/2016 5:29 PM
 */
public class BadRequestException extends RuntimeException
{
	private ErrorInfo errorInfo;

	public BadRequestException(String message, Integer code)
	{
		this(code, null);
	}

	public BadRequestException(String message)
	{
		this(null, message);
	}

	public BadRequestException(Integer code, String message)
	{
		this(new ErrorInfo(code, message));
	}

	public BadRequestException(ErrorInfo errorInfo)
	{
		this.errorInfo = errorInfo;
	}

	public ErrorInfo getErrorInfo()
	{
		return errorInfo;
	}

	private void setErrorInfo(ErrorInfo errorInfo)
	{
		this.errorInfo = errorInfo;
	}
}

package cn.blinkmind.promise.server.exception;

/**
 * @author jiaan.zhang@oracle.com
 * @date 05/10/2016 5:29 PM
 */
public class InvalidRequestException extends RuntimeException
{
	private ErrorInfo errorInfo;

	public InvalidRequestException(String message, Integer code)
	{
		this(code, null);
	}

	public InvalidRequestException(String message)
	{
		this(null, message);
	}

	public InvalidRequestException(Integer code, String message)
	{
		this(new ErrorInfo(code, message));
	}

	public InvalidRequestException(ErrorInfo errorInfo)
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

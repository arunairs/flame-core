package cn.blinkmind.duck.server.exception;

import java.lang.*;

public class InvalidRequestException extends RuntimeException
{
	private Error error;

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
		this(new Error(code, message));
	}

	public InvalidRequestException(Error error)
	{
		this.error = error;
	}

	public Error getError()
	{
		return error;
	}

	private void setError(Error error)
	{
		this.error = error;
	}
}

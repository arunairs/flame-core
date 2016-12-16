package cn.blinkmind.duck.server.exception;

public class InvalidDataException extends InvalidRequestException
{
	public InvalidDataException(Error error)
	{
		super(error);
	}
}

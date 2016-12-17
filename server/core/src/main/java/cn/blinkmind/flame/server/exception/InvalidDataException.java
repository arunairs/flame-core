package cn.blinkmind.flame.server.exception;

public class InvalidDataException extends InvalidRequestException
{
	public InvalidDataException(Error error)
	{
		super(error);
	}
}

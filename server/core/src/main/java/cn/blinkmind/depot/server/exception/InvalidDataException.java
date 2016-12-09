package cn.blinkmind.depot.server.exception;

/**
 * @author jiaan.zhang@outlook.com
 * @date 30/10/2016 10:22 PM
 */
public class InvalidDataException extends InvalidRequestException
{
	public InvalidDataException(Error error)
	{
		super(error);
	}
}

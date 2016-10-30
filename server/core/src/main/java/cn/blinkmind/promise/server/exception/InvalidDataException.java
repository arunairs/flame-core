package cn.blinkmind.promise.server.exception;

/**
 * @author jiaan.zhang@oracle.com
 * @date 30/10/2016 10:22 PM
 */
public class InvalidDataException extends InvalidRequestException
{
	public InvalidDataException(Error error)
	{
		super(error);
	}
}

package cn.blinkmind.depot.server.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;

/**
 * @author jiaan.zhang@outlook.com
 * @date 05/10/2016 5:30 PM
 */
public class Error
{
	private HttpStatus httpStatus;
	private String message;
	private Integer code;

	public Error(Integer code)
	{
		this(HttpStatus.BAD_REQUEST, code, null);
	}

	public Error(Integer code, String message)
	{
		this(HttpStatus.BAD_REQUEST, code, message);
	}

	public Error(HttpStatus httpStatus, Integer code, String message)
	{
		this.httpStatus = httpStatus;
		this.code = code;
		this.message = message;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public Integer getCode()
	{
		return code;
	}

	public void setCode(Integer code)
	{
		this.code = code;
	}

	@JsonIgnore
	public HttpStatus getHttpStatus()
	{
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus)
	{
		this.httpStatus = httpStatus;
	}

	public static void occurs(Error error)
	{
		throw new InvalidDataException(error);
	}
}

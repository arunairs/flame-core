package cn.blm.promise.server.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;

/**
 * @author jiaan.zhang@oracle.com
 * @date 05/10/2016 5:30 PM
 */
public class ErrorInfo
{
	private HttpStatus httpStatus;
	private String message;
	private Integer code;

	public ErrorInfo(Integer code)
	{
		this(HttpStatus.BAD_REQUEST, code, null);
	}

	public ErrorInfo(Integer code, String message)
	{
		this(HttpStatus.BAD_REQUEST, code, message);
	}

	public ErrorInfo(HttpStatus httpStatus, Integer code, String message)
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
}

package cn.blm.promise.server.exception;

/**
 * @author jiaan.zhang@oracle.com
 * @date 05/10/2016 5:30 PM
 */
public class ErrorInfo
{
	private String message;
	private Integer code;

	public ErrorInfo(Integer code)
	{
		this(code, null);
	}

	public ErrorInfo(Integer code, String message)
	{
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
}

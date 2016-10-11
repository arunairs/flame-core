package cn.blm.promise.server.advice;

import cn.blm.promise.server.exception.BadRequestException;
import cn.blm.promise.server.exception.ErrorInfo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author jiaan.zhang@oracle.com
 * @date 05/10/2016 5:15 PM
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler
{
	@ExceptionHandler(value = BadRequestException.class)
	@ResponseBody
	private ErrorInfo handleBadRequestException(BadRequestException exception)
	{
		return exception.getErrorInfo();
	}
}

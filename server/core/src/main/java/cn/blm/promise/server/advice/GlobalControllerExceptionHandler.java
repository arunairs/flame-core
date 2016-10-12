package cn.blm.promise.server.advice;

import cn.blm.promise.server.exception.BadRequestException;
import cn.blm.promise.server.exception.ErrorInfo;
import cn.blm.promise.server.exception.Errors;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * @author jiaan.zhang@oracle.com
 * @date 05/10/2016 5:15 PM
 */
@ControllerAdvice
@ResponseBody
public class GlobalControllerExceptionHandler
{
	@ExceptionHandler(value = BadRequestException.class)
	private ResponseEntity<ErrorInfo> handleBadRequestException(BadRequestException exception)
	{
		return ResponseEntity.status(exception.getErrorInfo().getHttpStatus()).body(exception.getErrorInfo());
	}

	@ExceptionHandler(value = DuplicateKeyException.class)
	private ResponseEntity<ErrorInfo> handleDuplicateKeyException(DuplicateKeyException exception)
	{
		return ResponseEntity.status(Errors.RESOURCE_ALREADY_EXISTS.getHttpStatus()).body(Errors.RESOURCE_ALREADY_EXISTS);
	}
}

package cn.blinkmind.promise.server.advice;

import cn.blinkmind.promise.server.repository.exception.ResourceNotFoundException;
import cn.blinkmind.promise.server.exception.InvalidRequestException;
import cn.blinkmind.promise.server.exception.Error;
import cn.blinkmind.promise.server.exception.Errors;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author jiaan.zhang@oracle.com
 * @date 05/10/2016 5:15 PM
 */
@ControllerAdvice
@ResponseBody
public class GlobalControllerExceptionHandler
{
	@ExceptionHandler(value = InvalidRequestException.class)
	private ResponseEntity<Error> handleBadRequestException(InvalidRequestException exception)
	{
		return ResponseEntity.status(exception.getError().getHttpStatus()).body(exception.getError());
	}

	@ExceptionHandler(value = DuplicateKeyException.class)
	private ResponseEntity<Error> handleDuplicateKeyException(DuplicateKeyException exception)
	{
		return ResponseEntity.status(Errors.RESOURCE_ALREADY_EXISTS.getHttpStatus()).body(Errors.RESOURCE_ALREADY_EXISTS);
	}

	@ExceptionHandler(value = ResourceNotFoundException.class)
	private ResponseEntity<Error> handleResourceNotFoundException(ResourceNotFoundException exception)
	{
		return ResponseEntity.status(Errors.RESOURCE_NOT_FOUND.getHttpStatus()).body(Errors.RESOURCE_NOT_FOUND);
	}
}

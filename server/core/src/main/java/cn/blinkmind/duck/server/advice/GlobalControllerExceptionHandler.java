package cn.blinkmind.duck.server.advice;

import cn.blinkmind.duck.server.exception.Errors;
import cn.blinkmind.duck.server.repository.exception.ResourceNotFoundException;
import cn.blinkmind.duck.server.exception.InvalidRequestException;
import cn.blinkmind.duck.server.exception.Error;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

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

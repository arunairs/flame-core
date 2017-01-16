package cn.blinkmind.flame.server.exception.handler;

import cn.blinkmind.flame.server.exception.Error;
import cn.blinkmind.flame.server.exception.Errors;
import cn.blinkmind.flame.server.exception.BadRequestException;
import cn.blinkmind.flame.server.repository.exception.ResourceNotFoundException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerExceptionHandler
{
    @ExceptionHandler(value = BadRequestException.class)
    private ResponseEntity<Error> handleBadRequestException(BadRequestException exception)
    {
        return ResponseEntity.status(exception.getError().getHttpStatus()).body(exception.getError());
    }

    @ExceptionHandler(value = DuplicateKeyException.class)
    private ResponseEntity<Error> handleDuplicateKeyException(DuplicateKeyException exception)
    {
        return ResponseEntity.status(Errors.RESOURCE_ALREADY_EXISTS.getError().getHttpStatus()).body(Errors.RESOURCE_ALREADY_EXISTS.getError());
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    private ResponseEntity<Error> handleResourceNotFoundException(ResourceNotFoundException exception)
    {
        return ResponseEntity.status(Errors.RESOURCE_NOT_FOUND.getError().getHttpStatus()).body(Errors.RESOURCE_NOT_FOUND.getError());
    }
}

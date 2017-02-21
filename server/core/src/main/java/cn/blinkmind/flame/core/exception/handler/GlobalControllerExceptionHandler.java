package cn.blinkmind.flame.core.exception.handler;

import cn.blinkmind.flame.core.exception.BadRequestException;
import cn.blinkmind.flame.core.exception.Error;
import cn.blinkmind.flame.core.exception.Errors;
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
}

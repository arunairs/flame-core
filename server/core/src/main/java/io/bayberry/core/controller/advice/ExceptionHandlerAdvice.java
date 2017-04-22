package io.bayberry.core.controller.advice;

import io.bayberry.core.exception.BadRequestException;
import io.bayberry.core.exception.Error;
import io.bayberry.core.exception.Errors;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice
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

package io.bayberry.core.exception;

import org.springframework.http.HttpStatus;

public class ResourceConflictException extends BadRequestException {

    public ResourceConflictException(Error error) {
        super(HttpStatus.CONFLICT, error);
    }
}

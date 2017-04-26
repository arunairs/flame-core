package io.bayberry.core.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends BadRequestException {

    public ResourceNotFoundException(Error error) {
        super(HttpStatus.NOT_FOUND, error);
    }
}

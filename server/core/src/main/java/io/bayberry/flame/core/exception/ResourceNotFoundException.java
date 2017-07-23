package io.bayberry.flame.core.exception;

import io.bayberry.flame.core.common.Error;
import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends BadRequestException {

    public ResourceNotFoundException(Error error) {
        super(HttpStatus.NOT_FOUND, error);
    }
}

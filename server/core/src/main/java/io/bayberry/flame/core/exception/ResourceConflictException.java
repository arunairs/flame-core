package io.bayberry.flame.core.exception;

import io.bayberry.flame.core.common.Error;
import org.springframework.http.HttpStatus;

public class ResourceConflictException extends BadRequestException {

    public ResourceConflictException(Error error) {
        super(HttpStatus.CONFLICT, error);
    }
}

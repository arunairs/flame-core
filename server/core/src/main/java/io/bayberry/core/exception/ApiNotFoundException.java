package io.bayberry.core.exception;

import io.bayberry.core.common.Error;

public class ApiNotFoundException extends ResourceNotFoundException {

    public ApiNotFoundException() {
        super(Error.API_NOT_FOUND);
    }
}

package io.bayberry.flame.core.exception;

import io.bayberry.flame.core.common.Error;

public class ApiNotFoundException extends ResourceNotFoundException {

    public ApiNotFoundException() {
        super(Error.API_NOT_FOUND);
    }
}

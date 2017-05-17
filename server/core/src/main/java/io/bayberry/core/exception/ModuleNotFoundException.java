package io.bayberry.core.exception;

import io.bayberry.core.common.Error;

public class ModuleNotFoundException extends ResourceNotFoundException {

    public ModuleNotFoundException() {
        super(Error.MODULE_NOT_FOUND);
    }
}

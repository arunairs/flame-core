package io.bayberry.flame.core.exception;

import io.bayberry.flame.core.common.Error;

public class ModuleNotFoundException extends ResourceNotFoundException {

    public ModuleNotFoundException() {
        super(Error.MODULE_NOT_FOUND);
    }
}

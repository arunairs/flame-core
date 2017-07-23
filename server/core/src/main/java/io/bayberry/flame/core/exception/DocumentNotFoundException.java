package io.bayberry.flame.core.exception;

import io.bayberry.flame.core.common.Error;

public class DocumentNotFoundException extends ResourceNotFoundException {

    public DocumentNotFoundException() {
        super(Error.DOCUMENT_NOT_FOUND);
    }
}

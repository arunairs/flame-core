package io.bayberry.core.exception;

import io.bayberry.core.common.Error;

public class DocumentNotFoundException extends ResourceNotFoundException {

    public DocumentNotFoundException() {
        super(Error.DOCUMENT_NOT_FOUND);
    }
}

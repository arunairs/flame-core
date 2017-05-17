package io.bayberry.core.exception;

import io.bayberry.core.common.Error;

public class BranchConflictException extends ResourceConflictException {

    public BranchConflictException() {
        super(Error.BRANCH_ALREADY_EXISTS);
    }
}

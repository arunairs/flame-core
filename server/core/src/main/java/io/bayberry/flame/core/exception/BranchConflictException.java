package io.bayberry.flame.core.exception;

import io.bayberry.flame.core.common.Error;

public class BranchConflictException extends ResourceConflictException {

    public BranchConflictException() {
        super(Error.BRANCH_ALREADY_EXISTS);
    }
}

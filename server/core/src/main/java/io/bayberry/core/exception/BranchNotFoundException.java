package io.bayberry.core.exception;

import io.bayberry.core.common.Error;

public class BranchNotFoundException extends ResourceNotFoundException {

    public BranchNotFoundException() {
        super(Error.BRANCH_NOT_FOUND);
    }
}

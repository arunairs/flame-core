package io.bayberry.flame.core.exception;

import io.bayberry.flame.core.common.Error;

public class BranchNotFoundException extends ResourceNotFoundException {

    public BranchNotFoundException() {
        super(Error.BRANCH_NOT_FOUND);
    }
}

package io.bayberry.flame.core.dto.validator;

import io.bayberry.flame.core.common.Error;
import io.bayberry.flame.core.dto.BranchRequest;
import io.bayberry.flame.core.exception.BadRequestException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class BranchRequestValidator extends Validator<BranchRequest> {

    @Override
    public boolean isValid(BranchRequest request) {
        if (StringUtils.isBlank(request.getName()))
            throw new BadRequestException(Error.BRANCH_NAME_IS_BLANK);
        return true;
    }
}

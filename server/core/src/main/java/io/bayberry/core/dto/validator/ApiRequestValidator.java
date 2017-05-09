package io.bayberry.core.dto.validator;

import io.bayberry.core.common.Error;
import io.bayberry.core.dto.ApiRequest;
import io.bayberry.core.exception.BadRequestException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class ApiRequestValidator extends Validator<ApiRequest> {

    @Override
    public boolean isValid(ApiRequest request) {
        if (StringUtils.isBlank(request.getName()))
            throw new BadRequestException(Error.API_NAME_IS_BLANK);
        return true;
    }
}

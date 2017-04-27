package io.bayberry.core.dto.validator;

import io.bayberry.core.common.Error;
import io.bayberry.core.dto.ModuleRequest;
import io.bayberry.core.exception.IllegalRequestParameterException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class ModuleRequestValidator extends Validator<ModuleRequest> {

    @Override
    public boolean isValid(ModuleRequest request) {
        if (StringUtils.isBlank(request.getName()))
            throw new IllegalRequestParameterException(Error.MODULE_NAME_IS_BLANK);
        return true;
    }
}

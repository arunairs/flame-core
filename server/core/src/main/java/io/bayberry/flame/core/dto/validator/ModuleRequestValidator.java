package io.bayberry.flame.core.dto.validator;

import io.bayberry.flame.core.common.Error;
import io.bayberry.flame.core.dto.ModuleRequest;
import io.bayberry.flame.core.exception.BadRequestException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class ModuleRequestValidator extends Validator<ModuleRequest> {

    @Override
    public boolean isValid(ModuleRequest request) {
        if (StringUtils.isBlank(request.getName()))
            throw new BadRequestException(Error.MODULE_NAME_IS_BLANK);
        return true;
    }
}

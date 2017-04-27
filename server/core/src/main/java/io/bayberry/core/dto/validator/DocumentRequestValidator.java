package io.bayberry.core.dto.validator;

import io.bayberry.core.common.Error;
import io.bayberry.core.dto.DocumentRequest;
import io.bayberry.core.exception.BadRequestException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class DocumentRequestValidator extends Validator<DocumentRequest> {

    @Override
    public boolean isValid(DocumentRequest request) {
        if (StringUtils.isBlank(request.getName()))
            throw new BadRequestException(Error.DOCUMENT_NAME_IS_BLANK);
        return true;
    }
}

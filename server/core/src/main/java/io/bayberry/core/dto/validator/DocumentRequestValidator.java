package io.bayberry.core.dto.validator;

import io.bayberry.core.dto.DocumentRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class DocumentRequestValidator extends Validator<DocumentRequest> {

    @Override
    public boolean isValid(DocumentRequest target) {
        if (StringUtils.isBlank(target.getName()))
            throw io.bayberry.core.exception.Errors.DOCUMENT_NAME_IS_BLANK;
        return true;
    }
}

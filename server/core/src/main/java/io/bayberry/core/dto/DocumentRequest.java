package io.bayberry.core.dto;

import io.bayberry.core.dto.validator.DocumentRequestValidator;
import io.bayberry.core.dto.validator.ValidatedBy;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ValidatedBy(value = DocumentRequestValidator.class)
public class DocumentRequest {

    private String name;
    private String description;
}

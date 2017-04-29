package io.bayberry.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.bayberry.core.dto.validator.DocumentRequestValidator;
import io.bayberry.core.dto.validator.ValidatedBy;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ValidatedBy(value = DocumentRequestValidator.class)
public class DocumentRequest {

    @JsonIgnore
    private Long id;
    private String name;
    private String description;
}

package io.bayberry.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.bayberry.core.dto.validator.ModuleRequestValidator;
import io.bayberry.core.dto.validator.ValidatedBy;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ValidatedBy(value = ModuleRequestValidator.class)
public class ModuleRequest {

    @JsonIgnore
    private Long id;
    @JsonIgnore
    private Long parentId;
    private String name;
    private String description;
}

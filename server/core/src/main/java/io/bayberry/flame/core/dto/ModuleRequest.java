package io.bayberry.flame.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.bayberry.flame.core.dto.validator.ModuleRequestValidator;
import io.bayberry.flame.core.dto.validator.ValidatedBy;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ValidatedBy(value = ModuleRequestValidator.class)
public class ModuleRequest {

    @JsonIgnore
    private Long id;
    private Long parentId;
    @JsonIgnore
    private Long branchId;
    private String name;
    private String description;
}

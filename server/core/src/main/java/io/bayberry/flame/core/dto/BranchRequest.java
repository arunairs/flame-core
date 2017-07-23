package io.bayberry.flame.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.bayberry.flame.core.dto.validator.BranchRequestValidator;
import io.bayberry.flame.core.dto.validator.ValidatedBy;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ValidatedBy(value = BranchRequestValidator.class)
public class BranchRequest {

    @JsonIgnore
    private Long id;
    private String name;
    @JsonIgnore
    private Long documentId;
}

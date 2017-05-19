package io.bayberry.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.bayberry.common.api.Request;
import io.bayberry.common.api.Response;
import io.bayberry.core.dto.validator.ApiRequestValidator;
import io.bayberry.core.dto.validator.ValidatedBy;
import io.bayberry.core.repository.entity.ApiType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ValidatedBy(value = ApiRequestValidator.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes({@JsonSubTypes.Type(value = HttpApiRequest.class)})
public abstract class ApiRequest {

    @JsonIgnore
    private Long id;
    @JsonIgnore
    private Long branchId;
    private Long moduleId;
    private String name;
    private String description;
    private ApiType type;

    public abstract Request getRequest();

    public abstract Response getResponse();
}

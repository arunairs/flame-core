package io.bayberry.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.bayberry.common.protocol.Protocol;
import io.bayberry.common.protocol.Request;
import io.bayberry.common.protocol.Response;
import io.bayberry.core.dto.validator.ApiRequestValidator;
import io.bayberry.core.dto.validator.ValidatedBy;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ValidatedBy(value = ApiRequestValidator.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "protocol")
@JsonSubTypes({@JsonSubTypes.Type(value = HttpApiRequest.class)})
public abstract class ApiRequest {

    @JsonIgnore
    private Long id;
    private String name;
    private String description;
    @JsonIgnore
    private Long branchId;
    private Long moduleId;

    public abstract Protocol getProtocol();

    public abstract Request getRequest();

    public abstract Response getResponse();
}

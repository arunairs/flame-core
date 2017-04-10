package io.bayberry.repository.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Transient;

@Getter
@Setter
@ToString(callSuper = true)
public abstract class Node extends BaseEntity<Long> {
    private String name;
    private String description;
    private Ref<Long> parent;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String path;
}

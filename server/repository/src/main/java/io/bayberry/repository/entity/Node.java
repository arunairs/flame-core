package io.bayberry.repository.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

@Getter
@Setter
@ToString
public abstract class Node implements Persistable<Long> {
    @Id
    private Long id;
    private String name;
    private String description;
    private Ref<Long> parent;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String path;
}

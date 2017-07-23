package io.bayberry.flame.core.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.bayberry.flame.core.constant.Fields;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.Instant;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BaseEntity implements Persistable<Long> {

    @Id
    @JsonProperty(value = Fields.ID)
    private Long id;
    private Long creatorId;
    private Long lastModifierId;
    private Instant createdTime;
    private Instant lastModifiedTime;
}

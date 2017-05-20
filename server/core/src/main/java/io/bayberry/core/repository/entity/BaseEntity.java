package io.bayberry.core.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.Instant;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BaseEntity implements Persistable<Long> {

    @Id
    private Long id;
    private Long creatorId;
    private Long lastModifierId;
    private Instant createdTime;
    private Instant lastModifiedTime;
}

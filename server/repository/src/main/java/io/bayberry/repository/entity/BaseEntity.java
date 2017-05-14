package io.bayberry.repository.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class BaseEntity implements Persistable<Long> {

    @Id
    private Long id;
    private Long creatorId;
    private Long lastModifierId;
    private LocalDateTime createdTime;
    private LocalDateTime lastModifiedTime;
}

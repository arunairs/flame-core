package io.bayberry.repository.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class BaseModel implements Persistable<Long> {

    @Id
    private Long id;
    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;
    private Long creatorId;
}

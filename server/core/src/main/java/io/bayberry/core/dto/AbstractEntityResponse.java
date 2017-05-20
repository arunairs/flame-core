package io.bayberry.core.dto;

import io.bayberry.common.util.BeanUtils;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public abstract class AbstractEntityResponse<T> {

    private Long id;
    private Long creatorId;
    private Instant createdTime;
    private Instant lastModifiedTime;

    public AbstractEntityResponse(T source) {
        BeanUtils.copyProperties(source, this);
    }
}

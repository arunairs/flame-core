package io.bayberry.core.dto;

import io.bayberry.common.util.BeanUtils;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class AbstractEntityResponse<T> {

    private Long id;
    private LocalDateTime createdTime;
    private LocalDateTime lastModifiedTime;
    private Long creatorId;

    public AbstractEntityResponse(T source) {
        BeanUtils.copyProperties(source, this);
    }
}

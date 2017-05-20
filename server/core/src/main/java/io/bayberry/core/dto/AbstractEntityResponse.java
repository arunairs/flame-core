package io.bayberry.core.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.bayberry.common.util.BeanUtils;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public abstract class AbstractEntityResponse<T> {

    private Long id;
    private Long creatorId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "GMT+8")
    private Instant createdTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "GMT+8")
    private Instant lastModifiedTime;

    public AbstractEntityResponse(T source) {
        BeanUtils.copyProperties(source, this);
    }
}

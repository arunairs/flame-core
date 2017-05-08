package io.bayberry.core.dto;

import io.bayberry.common.util.BeanUtils;
import io.bayberry.repository.entity.Document;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentResponse {

    private Long id;
    private String name;
    private String description;

    public DocumentResponse(Document document) {
        BeanUtils.copyProperties(document, this);
    }
}

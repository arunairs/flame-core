package io.bayberry.core.dto;

import io.bayberry.core.repository.entity.Document;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentResponse extends AbstractEntityResponse<Document> {

    private String name;
    private String description;

    public DocumentResponse(Document source) {
        super(source);
    }
}

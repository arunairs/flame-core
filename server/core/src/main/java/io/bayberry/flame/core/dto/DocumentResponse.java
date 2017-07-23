package io.bayberry.flame.core.dto;

import io.bayberry.flame.core.repository.entity.Document;
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

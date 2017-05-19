package io.bayberry.core.event;

import io.bayberry.core.repository.entity.Document;
import lombok.Getter;

@Getter
public class DocumentCreatedEvent extends AbstractEvent<Document> {

    public DocumentCreatedEvent(Document source) {
        super(source);
    }
}

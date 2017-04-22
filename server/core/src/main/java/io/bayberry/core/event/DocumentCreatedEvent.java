package io.bayberry.core.event;

import io.bayberry.repository.model.Document;
import lombok.Getter;

@Getter
public class DocumentCreatedEvent extends AbstractEvent<Document> {

    public DocumentCreatedEvent(Document source) {
        super(source);
    }
}

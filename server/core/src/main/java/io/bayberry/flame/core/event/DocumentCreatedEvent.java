package io.bayberry.flame.core.event;

import io.bayberry.flame.core.repository.entity.Document;
import lombok.Getter;

@Getter
public class DocumentCreatedEvent extends AbstractEvent<Document> {

    public DocumentCreatedEvent(Document source) {
        super(source);
    }
}

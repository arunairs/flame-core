package io.bayberry.core.event;

import io.bayberry.core.authentication.Auth;
import io.bayberry.repository.model.Document;
import lombok.Getter;

@Getter
public class DocumentCreatedEvent extends AbstractEvent<Document> {

    private Auth auth;

    public DocumentCreatedEvent(Document source, Auth auth) {
        super(source);
        this.auth = auth;
    }
}

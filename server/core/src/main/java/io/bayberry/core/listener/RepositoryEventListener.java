package io.bayberry.core.listener;

import io.bayberry.repository.model.BaseModel;
import io.bayberry.repository.event.BeforeUpdateAppliedEvent;
import io.bayberry.repository.util.IdGenerator;
import io.bayberry.repository.event.BeforeEntityCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RepositoryEventListener {
    private IdGenerator<Long> idGenerator;

    @Autowired
    public RepositoryEventListener(IdGenerator<Long> idGenerator) {
        this.idGenerator = idGenerator;
    }

    @EventListener
    public void handleBeforeEntityCreatedEvent(BeforeEntityCreatedEvent<Long> event) {
        BaseModel<Long> entity = (BaseModel<Long>) event.getSource();
        if (entity.getId() == null)
            entity.setId(idGenerator.nextId());
        if (entity.getCreatedDateTime() == null)
            entity.setCreatedDateTime(LocalDateTime.now());
    }

    @EventListener
    public void handleBeforeEntityUpdatedEvent(BeforeUpdateAppliedEvent event) {
        Update update = event.getSource();
        update.getUpdateObject().put("modifiedDateTime", LocalDateTime.now());
    }
}

package cn.blinkmind.flame.core.listener;

import cn.blinkmind.flame.repository.entity.BaseEntity;
import cn.blinkmind.flame.repository.event.BeforeUpdateAppliedEvent;
import cn.blinkmind.flame.repository.util.IdGenerator;
import cn.blinkmind.flame.repository.event.BeforeEntityCreatedEvent;
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
        BaseEntity<Long> entity = (BaseEntity<Long>) event.getSource();
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

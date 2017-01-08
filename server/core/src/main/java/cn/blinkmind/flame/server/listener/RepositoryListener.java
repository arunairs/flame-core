package cn.blinkmind.flame.server.listener;

import cn.blinkmind.flame.server.repository.entity.BasicEntity;
import cn.blinkmind.flame.server.repository.event.BeforeEntityCreatedEvent;
import cn.blinkmind.flame.server.repository.event.BeforeEntityUpdatedEvent;
import cn.blinkmind.flame.server.repository.event.BeforeUpdateAppliedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class RepositoryListener
{
    @EventListener
    public void handleBeforeEntityCreatedEvent(BeforeEntityCreatedEvent<Long> event)
    {
        BasicEntity<Long> entity = (BasicEntity<Long>) event.getSource();
        if (entity.getCreatedDate() == null)
            entity.setCreatedDate(new Date());
    }

    @EventListener
    public void handleBeforeEntityUpdatedEvent(BeforeUpdateAppliedEvent event)
    {
        Update update = event.getSource();
        update.getUpdateObject().put("updatedDate", new Date());
    }
}

package io.bayberry.flame.core.listener;

import io.bayberry.flame.core.repository.entity.BaseEntity;
import io.bayberry.flame.core.repository.id.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class MongoEventListener extends AbstractMongoEventListener<BaseEntity> {

    private IdGenerator<Long> idGenerator;

    @Autowired
    public MongoEventListener(IdGenerator<Long> idGenerator) {
        this.idGenerator = idGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<BaseEntity> event) {
        super.onBeforeConvert(event);

        BaseEntity entity = event.getSource();
        if (entity.getId() == null) {
            entity.setId(idGenerator.nextId());
        }
        if (entity.getCreatedTime() == null) {
            entity.setCreatedTime(Instant.now());
        } else {
            entity.setLastModifiedTime(Instant.now());
        }
    }
}

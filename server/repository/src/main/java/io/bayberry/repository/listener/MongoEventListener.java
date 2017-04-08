package io.bayberry.repository.listener;

import io.bayberry.repository.entity.BaseEntity;
import io.bayberry.repository.util.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MongoEventListener extends AbstractMongoEventListener<BaseEntity<Long>> {
    private IdGenerator<Long> idGenerator;

    @Autowired
    public MongoEventListener(IdGenerator<Long> idGenerator) {
        this.idGenerator = idGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<BaseEntity<Long>> event) {
        super.onBeforeConvert(event);

        BaseEntity<Long> entity = event.getSource();
        if (entity.getId() == null) {
            entity.setId(idGenerator.nextId());
        }
        if (entity.getCreatedDateTime() == null) {
            entity.setCreatedDateTime(LocalDateTime.now());
        } else {
            entity.setModifiedDateTime(LocalDateTime.now());
        }
    }
}

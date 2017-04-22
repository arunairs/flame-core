package io.bayberry.repository.listener;

import io.bayberry.repository.model.BaseModel;
import io.bayberry.repository.util.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MongoEventListener extends AbstractMongoEventListener<BaseModel> {
    private IdGenerator<Long> idGenerator;

    @Autowired
    public MongoEventListener(IdGenerator<Long> idGenerator) {
        this.idGenerator = idGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<BaseModel> event) {
        super.onBeforeConvert(event);

        BaseModel model = event.getSource();
        if (model.getId() == null) {
            model.setId(idGenerator.nextId());
        }
        if (model.getCreatedDateTime() == null) {
            model.setCreatedDateTime(LocalDateTime.now());
        } else {
            model.setModifiedDateTime(LocalDateTime.now());
        }
    }
}

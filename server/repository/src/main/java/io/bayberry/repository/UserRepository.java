package io.bayberry.repository;

import io.bayberry.repository.model.User;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends AbstractMongoRepository<User, Long> {

    public UserRepository(ApplicationEventPublisher applicationEventPublisher, MongoTemplate mongoTemplate) {
        super(applicationEventPublisher, mongoTemplate);
    }
}

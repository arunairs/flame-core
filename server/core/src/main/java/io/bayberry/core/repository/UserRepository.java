package io.bayberry.core.repository;

import io.bayberry.core.repository.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends AbstractMongoRepository<User, Long> {

    @Override
    public User insert(User entity) {
        return super.insert(entity);
    }
}

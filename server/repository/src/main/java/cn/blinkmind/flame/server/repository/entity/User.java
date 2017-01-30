package cn.blinkmind.flame.server.repository.entity;

import org.springframework.data.annotation.Transient;

@org.springframework.data.mongodb.core.mapping.Document(collection = "users")
public class User extends Account {

    @Override
    @Transient
    public User getCreator() {
        return super.getCreator();
    }
}

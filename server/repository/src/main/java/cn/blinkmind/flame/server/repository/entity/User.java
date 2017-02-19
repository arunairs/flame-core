package cn.blinkmind.flame.server.repository.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "users")
public class User extends Account {

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}

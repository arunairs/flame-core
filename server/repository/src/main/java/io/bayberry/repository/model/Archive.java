package io.bayberry.repository.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
public class Archive {

    private String description;
    private List<Long> moduleOrder;
    private List<Module> moduleEntities;
    private List<Interface> apis;
}

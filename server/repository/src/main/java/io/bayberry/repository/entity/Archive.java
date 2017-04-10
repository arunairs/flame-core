package io.bayberry.repository.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
public class Archive implements Persistable<Long> {
    @Id
    private Long id;
    private String description;
    private List<Ref<Long>> moduleOrder;
    private List<Module> modules;
    private List<Api> apis;
}

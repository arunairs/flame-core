package io.bayberry.repository.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Module extends BaseModel {
    private String name;
    private String description;
    private Long parentId;
    private List<Long> moduleOrder;
    private List<Long> apiOrder;
}

package io.bayberry.repository.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Transient;

import java.util.List;

@Getter
@Setter
public class Module extends BaseModel {

    private String name;
    private String description;
    private Long parentId;
    private List<Long> moduleOrder;
    private List<Long> apiOrder;
    @Transient
    private Long branchId;

    public boolean hasParent() {
        return this.parentId != null;
    }
}

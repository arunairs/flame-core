package io.bayberry.core.repository.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Transient;

import java.util.List;

@Getter
@Setter
public class Module extends BaseEntity {

    private String name;
    private String description;
    private Long parentId;
    private List<Long> moduleOrders;
    private List<Long> apiOrders;
    @Transient
    private Long branchId;

    public boolean hasParent() {
        return this.parentId != null;
    }
}

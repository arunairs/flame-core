package io.bayberry.core.dto;

import io.bayberry.common.util.BeanUtils;
import io.bayberry.repository.entity.Module;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModuleResponse {

    private Long id;
    private String name;
    private String description;
    private Long parentId;

    public ModuleResponse(Module module) {
        BeanUtils.copyProperties(module, this);
    }
}

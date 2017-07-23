package io.bayberry.flame.core.dto;

import io.bayberry.flame.core.repository.entity.Module;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModuleResponse extends AbstractEntityResponse<Module> {

    private String name;
    private String description;
    private Long parentId;

    public ModuleResponse(Module source) {
        super(source);
    }
}

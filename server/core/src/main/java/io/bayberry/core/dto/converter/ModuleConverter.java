package io.bayberry.core.dto.converter;

import io.bayberry.common.util.BeanUtils;
import io.bayberry.core.dto.ModuleRequest;
import io.bayberry.core.repository.entity.Module;
import org.springframework.stereotype.Component;

@Component
public class ModuleConverter {

    public Module convert(ModuleRequest request) {
        Module module = new Module();
        BeanUtils.copyProperties(request, module);
        return module;
    }
}

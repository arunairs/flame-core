package io.bayberry.flame.core.dto.converter;

import io.bayberry.flame.common.util.BeanUtils;
import io.bayberry.flame.core.dto.ModuleRequest;
import io.bayberry.flame.core.repository.entity.Module;
import org.springframework.stereotype.Component;

@Component
public class ModuleConverter {

    public Module convert(ModuleRequest request) {
        Module module = new Module();
        BeanUtils.copyProperties(request, module);
        return module;
    }
}

package io.bayberry.flame.core.dto.converter;

import com.google.common.collect.Maps;
import io.bayberry.flame.common.util.BeanUtils;
import io.bayberry.flame.common.util.ReflectionUtils;
import io.bayberry.flame.core.annotation.ApiInfo;
import io.bayberry.flame.core.dto.ApiRequest;
import io.bayberry.flame.core.repository.entity.Api;
import io.bayberry.flame.core.repository.entity.ApiType;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Set;

@Component
public class ApiConverter {

    private Map<ApiType, Class<? extends Api>> mappings = Maps.newHashMap();

    public Api convert(ApiRequest request) {
        Api api = this.getInstance(request.getType());
        BeanUtils.copyProperties(request, api);
        return api;
    }

    private Api getInstance(ApiType apiType) {
        try {
            return this.getMapping(apiType).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @PostConstruct
    private void init() {
        Set<Class<? extends Api>> subClasses = ReflectionUtils.getSubTypesOf(Api.class, Api.class);
        subClasses.stream().filter(subClass -> subClass.isAnnotationPresent(ApiInfo.class))
                .forEach(subClass -> this.setMapping(subClass.getAnnotation(ApiInfo.class).type(), subClass));
    }

    private void setMapping(ApiType apiType, Class<? extends Api> apiClass) {
        this.mappings.put(apiType, apiClass);
    }

    private Class<? extends Api> getMapping(ApiType apiType) {
        return this.mappings.get(apiType);
    }
}

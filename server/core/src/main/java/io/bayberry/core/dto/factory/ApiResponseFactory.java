package io.bayberry.core.dto.factory;

import com.google.common.collect.Maps;
import io.bayberry.common.util.BeanUtils;
import io.bayberry.common.util.ReflectionUtils;
import io.bayberry.core.annotation.ApiInfo;
import io.bayberry.core.dto.ApiResponse;
import io.bayberry.core.repository.entity.Api;
import io.bayberry.core.repository.entity.ApiType;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;

@Component
public class ApiResponseFactory {

    private Map<ApiType, Class<? extends ApiResponse>> mappings = Maps.newHashMap();

    public ApiResponse createFrom(Api api) {
        ApiResponse response = this.getInstance(api);
        BeanUtils.copyProperties(api, response);
        return response;
    }

    private ApiResponse getInstance(Api api) {
        try {
            Constructor<? extends ApiResponse> constructor = this.getMapping(api.getType()).getDeclaredConstructor(Api.class);
            constructor.setAccessible(true);
            return constructor.newInstance(api);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @PostConstruct
    private void init() {
        Set<Class<? extends ApiResponse>> subClasses = ReflectionUtils.getSubTypesOf(ApiResponse.class, ApiResponse.class);
        subClasses.stream().filter(subClass -> subClass.isAnnotationPresent(ApiInfo.class))
                .forEach(subClass -> this.setMapping(subClass.getAnnotation(ApiInfo.class).type(), subClass));
    }

    private void setMapping(ApiType apiType, Class<? extends ApiResponse> responseClass) {
        this.mappings.put(apiType, responseClass);
    }

    private Class<? extends ApiResponse> getMapping(ApiType apiType) {
        return this.mappings.get(apiType);
    }
}

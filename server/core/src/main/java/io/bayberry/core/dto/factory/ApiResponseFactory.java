package io.bayberry.core.dto.factory;

import com.google.common.collect.Maps;
import io.bayberry.common.protocol.Protocol;
import io.bayberry.common.util.BeanUtils;
import io.bayberry.common.util.ReflectionUtils;
import io.bayberry.core.dto.ApiResponse;
import io.bayberry.repository.annotation.ProtocolType;
import io.bayberry.repository.entity.Api;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;

@Component
public class ApiResponseFactory {

    private Map<Protocol, Class<? extends ApiResponse>> mappings = Maps.newHashMap();

    public ApiResponse createFrom(Api api) {
        ApiResponse response = this.getInstance(api);
        BeanUtils.copyProperties(api, response);
        return response;
    }

    private ApiResponse getInstance(Api api) {
        try {
            Constructor<? extends ApiResponse> constructor = this.getMapping(api.getProtocol()).getDeclaredConstructor(Api.class);
            constructor.setAccessible(true);
            return constructor.newInstance(api);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @PostConstruct
    private void init() {
        Set<Class<? extends ApiResponse>> subClasses = ReflectionUtils.getSubTypesOf(ApiResponse.class, ApiResponse.class);
        subClasses.stream().filter(subClass -> subClass.isAnnotationPresent(ProtocolType.class))
                .forEach(subClass -> this.setMapping(subClass.getAnnotation(ProtocolType.class).value(), subClass));
    }

    private void setMapping(Protocol protocol, Class<? extends ApiResponse> responseClass) {
        this.mappings.put(protocol, responseClass);
    }

    private Class<? extends ApiResponse> getMapping(Protocol protocol) {
        return this.mappings.get(protocol);
    }
}

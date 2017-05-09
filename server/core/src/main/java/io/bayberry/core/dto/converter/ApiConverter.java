package io.bayberry.core.dto.converter;

import com.google.common.collect.Maps;
import io.bayberry.common.protocol.Protocol;
import io.bayberry.common.util.BeanUtils;
import io.bayberry.common.util.ReflectionUtils;
import io.bayberry.core.dto.ApiRequest;
import io.bayberry.repository.annotation.ProtocolType;
import io.bayberry.repository.entity.Api;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Set;

@Component
public class ApiConverter {

    private Map<Protocol, Class<? extends Api>> mappings = Maps.newHashMap();

    public Api convert(ApiRequest request) {
        Api api = this.getInstance(request.getProtocol());
        BeanUtils.copyProperties(request, api);
        return api;
    }

    private Api getInstance(Protocol protocol) {
        try {
            return this.getMapping(protocol).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @PostConstruct
    private void init() {
        Set<Class<? extends Api>> subClasses = ReflectionUtils.getSubTypesOf(Api.class, Api.class);
        subClasses.stream().filter(subClass -> subClass.isAnnotationPresent(ProtocolType.class))
                .forEach(subClass -> this.setMapping(subClass.getAnnotation(ProtocolType.class).value(), subClass));
    }

    private void setMapping(Protocol protocol, Class<? extends Api> apiClass) {
        this.mappings.put(protocol, apiClass);
    }

    private Class<? extends Api> getMapping(Protocol protocol) {
        return this.mappings.get(protocol);
    }
}

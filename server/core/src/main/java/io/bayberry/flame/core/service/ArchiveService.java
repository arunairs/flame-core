package io.bayberry.flame.core.service;

import io.bayberry.flame.core.domain.Diff;
import io.bayberry.flame.core.domain.DiffResolver;
import io.bayberry.flame.core.repository.entity.Archive;
import org.apache.commons.lang3.ClassUtils;
import org.springframework.stereotype.Service;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.Collection;
import java.util.stream.Stream;

@Service
public class ArchiveService {

    public Diff<String> diff(Archive base, Archive head) {
        return resolveDiffs(base, head);
    }

    private Diff<String> resolveDiffs(Archive base, Archive head) {
        Diff<String> diff = new Diff<>();
        resolveDiffs(diff, "", Archive.class, base, head);
        return diff;
    }

    private <T> void resolveDiffs(Diff<String> diff, String path, Class<T> beanClass, T base, T head) {
        if (beanClass == null) return;
        try {
            Stream.of(Introspector.getBeanInfo(beanClass).getPropertyDescriptors())
                    .filter(descriptor -> !descriptor.getName().equals("class"))
                    .forEach(descriptor -> {
                        Class<?> propertyType = descriptor.getPropertyType();
                        if (Collection.class.isAssignableFrom(propertyType)) {

                        } else if (propertyType.isArray()) {

                        } else if (ClassUtils.isPrimitiveOrWrapper(propertyType) || propertyType.getPackage().getName().startsWith("java")) {
                            diff.add(DiffResolver.diff(descriptor.getName(), base, head), path.length() > 0 ? path + "/" + descriptor.getName() : descriptor.getName());
                        } else {

                        }
                        descriptor.getReadMethod().getReturnType();
                    });
        } catch (IntrospectionException e) {
            throw new RuntimeException(e);
        }
    }
}

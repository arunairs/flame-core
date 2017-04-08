package io.bayberry.core.domain;

import io.bayberry.core.bean.Diff;
import io.bayberry.repository.entity.Archive;
import io.bayberry.core.common.util.DiffUtils;
import org.apache.commons.lang3.ClassUtils;
import org.springframework.stereotype.Service;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.Collection;
import java.util.stream.Stream;

@Service
public class Archives {

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
                            diff.add(DiffUtils.diff(descriptor.getName(), base, head), path.length() > 0 ? path + "/" + descriptor.getName() : descriptor.getName());
                        } else {

                        }
                        descriptor.getReadMethod().getReturnType();
                    });
        } catch (IntrospectionException e) {
            throw new RuntimeException(e);
        }
    }
}

package cn.blinkmind.flame.core.service;

import cn.blinkmind.flame.core.bean.Diffs;
import cn.blinkmind.flame.repository.entity.Archive;
import cn.blinkmind.flame.core.common.util.DiffUtils;
import org.apache.commons.lang3.ClassUtils;
import org.springframework.stereotype.Service;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.Collection;
import java.util.stream.Stream;

@Service
public class ArchiveService {

    public Diffs<String> diff(Archive base, Archive head) {
        return resolveDiffs(base, head);
    }

    private Diffs<String> resolveDiffs(Archive base, Archive head) {
        Diffs<String> diffs = new Diffs<>();
        resolveDiffs(diffs, "", Archive.class, base, head);
        return diffs;
    }

    private <T> void resolveDiffs(Diffs<String> diffs, String path, Class<T> beanClass, T base, T head) {
        if (beanClass == null) return;
        try {
            Stream.of(Introspector.getBeanInfo(beanClass).getPropertyDescriptors())
                    .filter(descriptor -> !descriptor.getName().equals("class"))
                    .forEach(descriptor -> {
                        Class<?> propertyType = descriptor.getPropertyType();
                        if (Collection.class.isAssignableFrom(propertyType)) {

                        } else if (propertyType.isArray()) {

                        } else if (ClassUtils.isPrimitiveOrWrapper(propertyType) || propertyType.getPackage().getName().startsWith("java")) {
                            diffs.add(DiffUtils.diff(descriptor.getName(), base, head), path.length() > 0 ? path + "/" + descriptor.getName() : descriptor.getName());
                        } else {

                        }
                        descriptor.getReadMethod().getReturnType();
                    });
        } catch (IntrospectionException e) {
            throw new RuntimeException(e);
        }
    }
}

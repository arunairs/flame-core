package io.bayberry.common.util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import net.sf.cglib.beans.BeanCopier;

import java.util.concurrent.ExecutionException;

public class BeanUtils {

    private static final Cache<String, BeanCopier> cache = CacheBuilder.newBuilder().build();

    public static <S, T> void copy(S source, T target) {
        try {
            BeanCopier copier = cache.get(key(source.getClass(), target.getClass()), () -> BeanCopier.create(source.getClass(), target.getClass(), false));
            copier.copy(source, target, null);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private static <S, T> String key(Class<S> source, Class<T> target) {
        return source.getName() + ":" + target.getName();
    }
}

package cn.blinkmind.flame.server.util.patch;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Set;

@SuppressWarnings("unchecked")
public abstract class Patch<T, S>
{
    private static final String METHOD_PREFIX_GET = "get";
    private static final String METHOD_PREFIX_IS = "is";
    private static final String METHOD_PREFIX_SET = "set";

    private Class<T> targetClass;
    private T target;
    private T converted;
    private S source;
    private PatchListener.UpdateCallback updateCallback;
    private Set<PatchField> fields = new LinkedHashSet<>();

    protected Class<T> getTargetClass()
    {
        return targetClass;
    }

    protected S getSource()
    {
        return source;
    }

    protected T getTarget()
    {
        return target;
    }

    public Patch<T, S> mappedBy(final S source)
    {
        this.source = source;
        this.converted = null;
        return this;
    }

    protected Patch<T, S> bind(final T target)
    {
        this.target = target;
        this.targetClass = (Class<T>) this.target.getClass();
        return this;
    }

    public Patch<T, S> onUpdated(final PatchListener.UpdateCallback updateCallback)
    {
        this.updateCallback = updateCallback;
        return this;
    }

    public Patch<T, S> fields(final String... fields)
    {
        for (String field : fields)
        {
            this.fields.add(new PatchField(field));
        }
        return this;
    }

    public PatchField fields()
    {
        return new PatchField(this);
    }

    public void apply()
    {
        fields.forEach(field ->
                apply(getTarget(), field.name, getSource(), field.mappedBy, getConverted())
        );
    }

    public void apply(final T target)
    {
        this.bind(target);
        this.apply();
    }

    protected T getConverted()
    {
        if (this.converted == null)
        {
            this.converted = convert(source);
        }
        return converted;
    }

    protected abstract T convert(final S source);

    protected abstract boolean contains(final S source, final String fieldName);

    protected <V> boolean apply(final T target, final String targetFieldName, final S source, final String mappedBy, final T converted)
    {
        if (source == null || !contains(source, mappedBy)) return false;
        try
        {
            Class<T> targetClass = getTargetClass();
            Field field = targetClass.getDeclaredField(targetFieldName);
            field.setAccessible(true);
            Class<?> targetFieldClass = field.getType();
            String fieldName = StringUtils.capitalize(targetFieldName);
            Method setterMethod = targetClass.getDeclaredMethod(METHOD_PREFIX_SET + fieldName, targetFieldClass);
            setterMethod.setAccessible(true);

            Method getterMethod;
            if (boolean.class.equals(targetFieldClass) || Boolean.class.equals(targetFieldClass))
                getterMethod = targetClass.getDeclaredMethod(METHOD_PREFIX_IS + fieldName);
            else
                getterMethod = targetClass.getDeclaredMethod(METHOD_PREFIX_GET + fieldName);
            getterMethod.setAccessible(true);
            V previous = (V) getterMethod.invoke(target);
            V current = (V) getterMethod.invoke(converted);
            setterMethod.invoke(target, current);
            if (updateCallback != null)
                updateCallback.callback(new PatchEvent.UpdateEvent<>(targetFieldName, mappedBy, previous, current, getConverted()));
        }
        catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | NoSuchFieldException e)
        {
            throw new RuntimeException(e);
        }
        return true;
    }

    public class PatchField
    {
        private Patch<T, S> patch;
        private String name;
        private String mappedBy;

        private PatchField(final String name, final String mappedBy)
        {
            this.name = name;
            this.mappedBy = mappedBy;
        }

        private PatchField(final String field)
        {
            this(field, field);
        }

        private PatchField(final Patch<T, S> patch)
        {
            this.patch = patch;
        }

        public PatchField add(final String field)
        {
            fields.add(new PatchField(field, field));
            return this;
        }

        public PatchField add(final String field, final String mappedBy)
        {
            fields.add(new PatchField(field, mappedBy));
            return this;
        }

        public Patch<T, S> end()
        {
            return this.patch;
        }
    }
}

package io.bayberry.flame.core.event;

import lombok.Getter;

@Getter
public abstract class AbstractEvent<T> implements Event<T> {

    private T source;

    public AbstractEvent(T source) {
        this.source = source;
    }
}

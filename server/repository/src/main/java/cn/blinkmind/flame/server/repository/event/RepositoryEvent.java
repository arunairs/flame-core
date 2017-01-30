package cn.blinkmind.flame.server.repository.event;

import org.springframework.context.ApplicationEvent;

public abstract class RepositoryEvent<T> extends ApplicationEvent {

    public RepositoryEvent(T source) {
        super(source);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T getSource() {
        return (T) super.getSource();
    }
}

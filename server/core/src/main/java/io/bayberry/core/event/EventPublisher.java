package io.bayberry.core.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher {

    @Autowired
    private ApplicationEventPublisher publisher;

    public void publish(Event<?> event) {
        publisher.publishEvent(event);
    }
}

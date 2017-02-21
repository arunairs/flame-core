package cn.blinkmind.flame.repository.event;

import org.springframework.data.mongodb.core.query.Update;

public class BeforeUpdateAppliedEvent extends RepositoryEvent<Update> {

    public BeforeUpdateAppliedEvent(Update source) {
        super(source);
    }
}

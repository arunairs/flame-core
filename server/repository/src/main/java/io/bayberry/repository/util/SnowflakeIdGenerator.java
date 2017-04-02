package io.bayberry.repository.util;

import com.ge.snowizard.core.IdWorker;
import com.ge.snowizard.exceptions.InvalidSystemClock;

public class SnowflakeIdGenerator implements IdGenerator<Long> {

    private final IdWorker idWorker;

    public SnowflakeIdGenerator(final int workerId, final int dataCenterId) {
        this.idWorker = new IdWorker(workerId, dataCenterId);
    }

    @Override
    public Long nextId() {
        try {
            return idWorker.nextId();
        } catch (InvalidSystemClock invalidSystemClock) {
            throw new RuntimeException(invalidSystemClock);
        }
    }
}

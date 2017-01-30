package cn.blinkmind.flame.server.repository.util;

import com.ge.snowizard.core.IdWorker;
import com.ge.snowizard.exceptions.InvalidSystemClock;

public class SnowflakeIdGenerator implements IdGenerator<Long> {

    private IdWorker idWorker;

    public SnowflakeIdGenerator(int workerId, int dataCenterId) {
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

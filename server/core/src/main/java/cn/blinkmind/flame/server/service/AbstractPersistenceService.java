package cn.blinkmind.flame.server.service;

import com.ge.snowizard.core.IdWorker;
import com.ge.snowizard.exceptions.InvalidSystemClock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

abstract class AbstractPersistenceService
{
    @Autowired
    private IdWorker idWorker;

    public long newId()
    {
        try
        {
            return idWorker.nextId();
        }
        catch (InvalidSystemClock invalidSystemClock)
        {
            invalidSystemClock.printStackTrace();
        }
        return -1;
    }

    public String newIdAsString()
    {
        return String.valueOf(newId());
    }
}

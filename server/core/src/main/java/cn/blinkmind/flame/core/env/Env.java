package cn.blinkmind.flame.core.env;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Env
{
    @Value("#{systemProperties['HOST_WORK_ID']?:0}")
    private int hostWorkId;

    @Value("#{systemProperties['HOST_DATA_CENTER_ID']?:0}")
    private int hostDataCenterId;

    public int getHostWorkId()
    {
        return hostWorkId;
    }

    public int getHostDataCenterId()
    {
        return hostDataCenterId;
    }
}

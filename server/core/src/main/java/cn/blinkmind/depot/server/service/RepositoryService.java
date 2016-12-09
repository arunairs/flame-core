package cn.blinkmind.depot.server.service;

import com.ge.snowizard.core.IdWorker;
import com.ge.snowizard.exceptions.InvalidSystemClock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jiaan.zhang@outlook.com
 * @date 04/10/2016 2:09 AM
 */
@Service
public class RepositoryService
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

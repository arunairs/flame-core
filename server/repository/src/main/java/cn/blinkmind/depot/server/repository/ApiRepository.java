package cn.blinkmind.depot.server.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author jiaan.zhang@outlook.com
 * @date 26/09/2016 4:21 PM
 */
@Repository
public class ApiRepository
{
	@Autowired
	private ArchiveRepository archiveRepository;
}

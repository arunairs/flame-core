package cn.blinkmind.promise.server.service;

import cn.blinkmind.promise.server.exception.Error;
import cn.blinkmind.promise.server.exception.Errors;
import cn.blinkmind.promise.server.repository.ModuleRepository;
import cn.blinkmind.promise.server.repository.entity.Archive;
import cn.blinkmind.promise.server.repository.entity.Module;
import cn.blinkmind.promise.server.repository.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author jiaan.zhang@oracle.com
 * @date 27/10/2016 11:20 PM
 */
@Service
public class ModuleService
{
	@Autowired
	private ModuleRepository moduleRepository;

	@Autowired
	private ApiService apiService;

	@Autowired
	private RepositoryService repositoryService;

	public Module fill(Module module, Archive archive, User creator)
	{
		if (StringUtils.isBlank(module.getName()))
			Error.occurs(Errors.MODULE_NAME_IS_BLANK);

		module.clean();
		module.setId(repositoryService.newId());
		module.setCreator(creator);
		module.refreshCreatedDate();
		module.setArchive(archive);
		apiService.fill(module.getApis(), module, creator);
		return module;
	}

	public Collection<Module> fill(Collection<Module> modules, Archive archive, User creator)
	{
		if (modules != null)
			for (Module module : modules)
				fill(module, archive, creator);
		return modules;
	}

	public Module fillAndPersist(Module module, Archive archive, User creator)
	{
		fill(module, archive, creator);
		moduleRepository.insert(module);
		return module;
	}
}

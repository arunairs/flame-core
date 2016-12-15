package cn.blinkmind.depot.server.service;

import cn.blinkmind.depot.server.exception.Assertion;
import cn.blinkmind.depot.server.exception.Errors;
import cn.blinkmind.depot.server.repository.ModuleRepository;
import cn.blinkmind.depot.server.repository.entity.Archive;
import cn.blinkmind.depot.server.repository.entity.CrudType;
import cn.blinkmind.depot.server.repository.entity.Module;
import cn.blinkmind.depot.server.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author jiaan.zhang@outlook.com
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

	public Module assemble(Module module, Archive archive, User creator)
	{
		Assertion.notBlank(module.getName(), Errors.MODULE_NAME_IS_BLANK);

		module.cleanup(CrudType.CREATE);
		module.setId(repositoryService.newId());
		module.setCreator(creator);
		module.refreshCreatedDate();
		module.setArchive(archive);
		apiService.assemble(module.getApis(), module, creator);
		return module;
	}

	public Collection<Module> assemble(Collection<Module> modules, Archive archive, User creator)
	{
		if (modules != null)
			for (Module module : modules)
				assemble(module, archive, creator);
		return modules;
	}

	public Module create(Module module, Archive archive, User creator)
	{
		assemble(module, archive, creator);
		return module;
	}
}

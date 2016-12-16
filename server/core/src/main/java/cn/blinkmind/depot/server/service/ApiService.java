package cn.blinkmind.depot.server.service;

import cn.blinkmind.depot.server.bean.web.Request;
import cn.blinkmind.depot.server.exception.Errors;
import cn.blinkmind.depot.server.repository.entity.CrudType;
import cn.blinkmind.depot.server.repository.entity.Module;
import cn.blinkmind.depot.server.repository.entity.User;
import cn.blinkmind.depot.server.exception.Assertion;
import cn.blinkmind.depot.server.repository.entity.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ApiService
{
	@Autowired
	private RepositoryService repositoryService;

	public Api assemble(Api api, Module module, User creator)
	{
		api.setModule(module);
		Request request = api.getRequest();

		Assertion.isFalse(module == null || module.getId() == null, Errors.API_MODULE_IS_NOT_SPECIFIED);
		Assertion.notNull(request, Errors.API_REQUEST_IS_NULL);
		Assertion.notBlank(api.getScheme(), Errors.REQUEST_SCHEME_IS_BLANK);
		Assertion.notBlank(api.getUri(), Errors.REQUEST_URI_IS_BLANK);

		api.cleanup(CrudType.CREATE);
		api.setId(repositoryService.newId());
		api.setCreator(creator);
		api.refreshCreatedDate();
		return api;
	}

	public Collection<Api> assemble(Collection<Api> apis, Module module, User creator)
	{
		if (apis != null)
			for (Api api : apis)
				assemble(api, module, creator);
		return apis;
	}
}

package cn.blinkmind.promise.server.service;

import cn.blinkmind.promise.server.bean.web.Request;
import cn.blinkmind.promise.server.exception.Assertion;
import cn.blinkmind.promise.server.exception.Errors;
import cn.blinkmind.promise.server.repository.ApiRepository;
import cn.blinkmind.promise.server.repository.entity.Api;
import cn.blinkmind.promise.server.repository.entity.CRUD;
import cn.blinkmind.promise.server.repository.entity.Module;
import cn.blinkmind.promise.server.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author jiaan.zhang@oracle.com
 * @date 29/10/2016 10:43 PM
 */
@Service
public class ApiService
{
	@Autowired
	private ApiRepository apiRepository;

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

		api.cleanup(CRUD.CREATE);
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

	public Api create(Api api, Module module, User creator)
	{
		assemble(api, module, creator);
		apiRepository.insert(api);
		return api;
	}
}

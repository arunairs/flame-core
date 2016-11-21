package cn.blinkmind.promise.server.service;

import cn.blinkmind.promise.server.bean.web.Request;
import cn.blinkmind.promise.server.exception.Error;
import cn.blinkmind.promise.server.exception.Errors;
import cn.blinkmind.promise.server.repository.ApiRepository;
import cn.blinkmind.promise.server.repository.entity.Api;
import cn.blinkmind.promise.server.repository.entity.Module;
import cn.blinkmind.promise.server.repository.entity.User;
import org.apache.commons.lang3.StringUtils;
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

	public Api fill(Api api, Module module, User creator)
	{
		if (module == null || module.getId() == null)
			Error.occurs(Errors.API_MODULE_IS_NOT_SPECIFIED);

		Request request = api.getRequest();
		if (request == null)
			Error.occurs(Errors.API_REQUEST_IS_NULL);

		api.setModule(module);
		if (StringUtils.isBlank(api.getScheme()))
			Error.occurs(Errors.REQUEST_SCHEME_IS_BLANK);

		if (StringUtils.isBlank(api.getUri()))
			Error.occurs(Errors.REQUEST_URI_IS_BLANK);

		api.clean();
		api.setId(repositoryService.newId());
		api.setCreator(creator);
		api.refreshCreatedDate();
		return api;
	}

	public Collection<Api> fill(Collection<Api> apis, Module module, User creator)
	{
		if (apis != null)
			for (Api api : apis)
				fill(api, module, creator);
		return apis;
	}

	public Api fillAndPersist(Api api, Module module, User creator)
	{
		fill(api, module, creator);
		apiRepository.insert(api);
		return api;
	}
}

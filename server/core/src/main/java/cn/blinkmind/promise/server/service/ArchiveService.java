package cn.blinkmind.promise.server.service;

import cn.blinkmind.promise.server.exception.Error;
import cn.blinkmind.promise.server.exception.Errors;
import cn.blinkmind.promise.server.repository.ApiRepository;
import cn.blinkmind.promise.server.repository.ArchiveRepository;
import cn.blinkmind.promise.server.repository.ModuleRepository;
import cn.blinkmind.promise.server.repository.entity.*;
import cn.blinkmind.promise.server.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author jiaan.zhang@oracle.com
 * @date 17/10/2016 12:41 AM
 */
@Service
public class ArchiveService
{
	@Autowired
	private ApiRepository apiRepository;

	@Autowired
	private ArchiveRepository archiveRepository;

	@Autowired
	private ModuleRepository moduleRepository;

	@Autowired
	private ApiService apiService;

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private ModuleService moduleService;

	//创建document后应调用create创建一个临时的archive，临时的archive没有版本号和分支信息
	public Archive fill(Archive archive, Document document, User creator)
	{
		if (document == null || document.getId() == null)
			Error.occurs(Errors.ARCHIVE_DOCUMENT_IS_NOT_SPECIFIED);

		archive.clean();
		archive.setId(repositoryService.newId());
		archive.setCreator(creator);
		archive.setDocument(document);
		archive.refreshCreatedDate();
		moduleService.fill(archive.getModules(), archive, creator);
		addModuleNodes(archive, archive.getModules());
		return archive;
	}

	public Archive fillAndPersist(Archive archive, Document document, User creator)
	{
		fill(archive, document, creator);
		archiveRepository.insert(archive);
		return archive;
	}

	private Archive addModuleNode(Archive archive, Module module)
	{
		Node moduleNode = new Node(module.getId());
		Set<Api> apis = module.getApis();
		if (apis != null)
			for (Api api : apis)
			{
				Node apiNode = new Node(api.getId());
				moduleNode.addChild(apiNode);
			}
		archive.getNodes().add(moduleNode);
		return archive;
	}

	private Archive addModuleNodes(Archive archive, Collection<Module> modules)
	{
		if (modules != null)
			for (Module module : modules)
				addModuleNode(archive, module);
		return archive;
	}

	//手动发布后archive带上版本消息，并且不再是临时文件，其他人也能看到
	public Archive update(Archive current, Map<String, Object> patch, User user)
	{
		BeanUtil.patch(current, "", patch, "");
		current.refreshCreatedDate();
		return current;
	}
}

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

import java.util.*;

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
		persistApis(archive);
		moduleRepository.insertAll(archive.getModules());
		archiveRepository.insert(archive);
		return archive;
	}

	private void persistApis(Archive archive)
	{
		List<Module> modules = archive.getModules();
		Set<Api> apisToPersist = new LinkedHashSet<>();
		if (modules != null)
			for (Module module : modules)
			{
				List<Api> apis = module.getApis();
				if (apis != null)
				{
					apisToPersist.addAll(apis);
				}
			}
		if (apisToPersist.size() > 0)
			apiRepository.insertAll(apisToPersist);
	}

	private Archive addModuleNode(Archive archive, Module module, int ordinal)
	{
		Node moduleNode = new Node(module.getId(), ordinal);
		List<Api> apis = module.getApis();
		if (apis != null)
		{
			int apiOrdinal = 0;
			for (Api api : apis)
			{
				Node apiNode = new Node(api.getId(), apiOrdinal++);
				moduleNode.addChild(apiNode);
			}
		}
		archive.getNodes().add(moduleNode);
		return archive;
	}

	private Archive addModuleNodes(Archive archive, Collection<Module> modules)
	{
		if (modules != null)
		{
			int ordinal = 0;
			for (Module module : modules)
				addModuleNode(archive, module, ordinal++);
		}
		return archive;
	}

	//手动发布后archive带上版本消息，并且不再是临时文件，其他人也能看到
	public Archive update(Archive current, Map<String, Object> patch, User user)
	{
		BeanUtil.patch(current, "", patch, "");
		current.refreshCreatedDate();
		return current;
	}

	public Archive get(final long id, final User user)
	{
		Archive archive = archiveRepository.require(id);
		Set<Node> moduleNodes = archive.getNodes();
		if (moduleNodes != null && moduleNodes.size() > 0)
		{
			final LinkedList<Long> moduleIds = new LinkedList<>();
			final LinkedList<Long> apiIds = new LinkedList<>();
			final Map<Long, Long> parentMap = new HashMap<>();
			final Map<Long, Node> apiNodeMap = new HashMap<>();
			final Map<Long, Module> moduleMap = new HashMap<>();
			for (Node moduleNode : moduleNodes)
			{
				moduleIds.add(moduleNode.getId());
				Set<Node> apiNodes = moduleNode.getChildren();
				if (apiNodes != null)
				{
					for (Node apiNode : apiNodes)
					{
						apiIds.add(apiNode.getId());
						parentMap.put(apiNode.getId(), moduleNode.getId());
						apiNodeMap.put(apiNode.getId(), apiNode);
					}
				}
			}
			ArrayList<Module> modules = (ArrayList<Module>) moduleRepository.findIn(moduleIds);
			if (modules != null)
			{
				for (Module module : modules)
				{
					module.setArchive(archive);
					moduleMap.put(module.getId(), module);
				}
			}
			ArrayList<Api> apis = (ArrayList<Api>) apiRepository.findIn(apiIds);
			if (apis != null)
			{
				apis.sort((o1, o2) -> apiNodeMap.get(o1.getId()).getOrdinal() - apiNodeMap.get(o2.getId()).getOrdinal());
				for (Api api : apis)
				{
					moduleMap.get(parentMap.get(api.getId())).addApi(api);
				}
			}
			archive.setModules(modules);
		}
		return archive;
	}
}

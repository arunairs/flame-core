package cn.blinkmind.promise.server.service;

import cn.blinkmind.promise.server.bean.patch.JSONPatch;
import cn.blinkmind.promise.server.bean.patch.Patch;
import cn.blinkmind.promise.server.exception.Assertion;
import cn.blinkmind.promise.server.exception.Errors;
import cn.blinkmind.promise.server.repository.ApiRepository;
import cn.blinkmind.promise.server.repository.ArchiveRepository;
import cn.blinkmind.promise.server.repository.ModuleRepository;
import cn.blinkmind.promise.server.repository.entity.*;
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

	public Archive assemble(Archive archive, Document document, User creator)
	{
		Assertion.isFalse(document == null || document.getId() == null, Errors.ARCHIVE_DOCUMENT_IS_NOT_SPECIFIED);

		archive.cleanup(CRUD.CREATE);
		archive.setId(repositoryService.newId());
		archive.setCreator(creator);
		archive.setDocument(document);
		archive.refreshCreatedDate();
		moduleService.assemble(archive.getModules(), archive, creator);
		addModuleNodes(archive, archive.getModules());
		return archive;
	}

	public Archive create(Archive archive, Document document, User creator)
	{
		assemble(archive, document, creator);
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

	@SuppressWarnings("unchecked")
	public Archive update(Archive archive, Map<String, Object> patch, User user)
	{
		Assertion.isTrue(archive.isUpdatable(), Errors.ARCHIVE_IS_ALREADY_RELEASED);

		final Patch.Operation operation = new JSONPatch<Archive>().source(patch).target(archive)
				.build((Archive converted) ->
				{
					if (converted.getRequest() != null)
						converted.getRequest().setMethods(null);
					return converted;
				})
				.update("description")
				.update("status")
				.update("request");

		if (archive.isReleased())
		{
			operation.update("version");
		}

		this.validate(archive);
		archive.refreshUpdatedDate();
		//archiveRepository.update(archive);
		return archive;
	}

	private void validate(Archive archive)
	{
		Assertion.notNull(archive.getStatus(), Errors.ARCHIVE_STATUS_IS_NULL);
		if (archive.isReleased())
		{
			Assertion.notNull(archive.getVersion(), Errors.ARCHIVE_VERSION_IS_NULL);
		}
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
				apis.sort(Comparator.comparingInt(o -> apiNodeMap.get(o.getId()).getOrdinal()));
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

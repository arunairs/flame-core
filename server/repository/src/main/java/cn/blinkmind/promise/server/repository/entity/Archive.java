package cn.blinkmind.promise.server.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Transient;

import java.util.*;

/**
 * @author jiaan.zhang@oracle.com
 * @date 26/09/2016 2:40 PM
 */
@org.springframework.data.mongodb.core.mapping.Document(collection = "archives")
public class Archive extends BaseEntity
{
	private Version version;
	private String description;
	private Branch branch;
	private ArchiveStatus status;
	private Ref<Long> documentRef;
	private Document document;
	private Set<Node> nodes = new LinkedHashSet<>();
	private Set<Module> modules = new LinkedHashSet<>();

	public Version getVersion()
	{
		return version;
	}

	public void setVersion(Version version)
	{
		this.version = version;
	}

	@JsonIgnore
	public Ref<Long> getDocumentRef()
	{
		return documentRef;
	}

	private void setDocumentRef(Ref<Long> documentRef)
	{
		this.documentRef = documentRef;
	}

	@Transient
	public Document getDocument()
	{
		return document;
	}

	public void setDocument(Document document)
	{
		this.document = document;
		if (this.document != null)
			setDocumentRef(new Ref<>(this.document));
		else setDocumentRef(null);
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Branch getBranch()
	{
		return branch;
	}

	public void setBranch(Branch branch)
	{
		this.branch = branch;
	}

	public ArchiveStatus getStatus()
	{
		return status;
	}

	public void setStatus(ArchiveStatus status)
	{
		this.status = status;
	}

	@JsonIgnore
	public Set<Node> getNodes()
	{
		return nodes;
	}

	public void setNodes(Set<Node> nodes)
	{
		this.nodes = nodes;
	}

	@Transient
	public Set<Module> getModules()
	{
		return modules;
	}

	public void setModules(Set<Module> modules)
	{
		this.modules = modules;
	}
}

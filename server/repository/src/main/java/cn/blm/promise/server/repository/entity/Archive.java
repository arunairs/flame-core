package cn.blm.promise.server.repository.entity;

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
	private Ref<Long> documentRef;
	private Document document;
	private Map<Ref<Long>, LinkedHashSet<Ref<Long>>> structure = new LinkedHashMap<>();
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

	@JsonIgnore
	public Map<Ref<Long>, LinkedHashSet<Ref<Long>>> getStructure()
	{
		return structure;
	}

	public void setStructure(Map<Ref<Long>, LinkedHashSet<Ref<Long>>> structure)
	{
		this.structure = structure;
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

package cn.blm.promise.server.repository.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

/**
 * @author jiaan.zhang@oracle.com
 * @date 26/09/2016 2:40 PM
 */
@Document(collection = "archives")
public class Archive extends BaseEntity
{
	private Version version;
	private String description;
	private BranchType branchType;
	private Document document;
	private Set<Module> modules = new LinkedHashSet<>();

	public Version getVersion()
	{
		return version;
	}

	public void setVersion(Version version)
	{
		this.version = version;
	}

	public Document getDocument()
	{
		return document;
	}

	public void setDocument(Document document)
	{
		this.document = document;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public BranchType getBranchType()
	{
		return branchType;
	}

	public void setBranchType(BranchType branchType)
	{
		this.branchType = branchType;
	}

	public Set<Module> getModules()
	{
		return modules;
	}

	public void setModules(Set<Module> modules)
	{
		this.modules = modules;
	}
}

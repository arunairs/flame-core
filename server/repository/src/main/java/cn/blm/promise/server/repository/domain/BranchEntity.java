package cn.blm.promise.server.repository.domain;

import cn.blm.promise.server.repository.enumeration.BranchType;

import javax.persistence.Entity;

/**
 * @author jiaan.zhang@oracle.com
 * @date 26/09/2016 2:40 PM
 */
@Entity(name = "branch")
public class BranchEntity extends AbstractEntity
{
	private Integer major;
	private Integer minor;
	private Integer patch;
	private Long documentId;
	private String description;
	private BranchType branchType;

	public Integer getMajor()
	{
		return major;
	}

	public void setMajor(Integer major)
	{
		this.major = major;
	}

	public Integer getMinor()
	{
		return minor;
	}

	public void setMinor(Integer minor)
	{
		this.minor = minor;
	}

	public Integer getPatch()
	{
		return patch;
	}

	public void setPatch(Integer patch)
	{
		this.patch = patch;
	}

	public Long getDocumentId()
	{
		return documentId;
	}

	public void setDocumentId(Long documentId)
	{
		this.documentId = documentId;
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
}

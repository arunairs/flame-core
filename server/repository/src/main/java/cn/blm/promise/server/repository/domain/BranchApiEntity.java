package cn.blm.promise.repository.domain;

/**
 * @author jiaan.zhang@oracle.com
 * @date 26/09/2016 4:18 PM
 */
public class BranchApiEntity extends AbstractEntity
{
	private Long branchId;
	private Long moduleId;
	private Long apiId;
	private Long sourceApiId;

	public Long getBranchId()
	{
		return branchId;
	}

	public void setBranchId(Long branchId)
	{
		this.branchId = branchId;
	}

	public Long getModuleId()
	{
		return moduleId;
	}

	public void setModuleId(Long moduleId)
	{
		this.moduleId = moduleId;
	}

	public Long getApiId()
	{
		return apiId;
	}

	public void setApiId(Long apiId)
	{
		this.apiId = apiId;
	}

	public Long getSourceApiId()
	{
		return sourceApiId;
	}

	public void setSourceApiId(Long sourceApiId)
	{
		this.sourceApiId = sourceApiId;
	}
}

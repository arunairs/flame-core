package cn.blm.promise.repository.domain;

/**
 * @author jiaan.zhang@oracle.com
 * @date 26/09/2016 4:11 PM
 */
public class BranchModuleEntity extends AbstractEntity
{
	private Long branchId;
	private Long moduleId;
	private Long sourceModuleId;

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

	public Long getSourceModuleId()
	{
		return sourceModuleId;
	}

	public void setSourceModuleId(Long sourceModuleId)
	{
		this.sourceModuleId = sourceModuleId;
	}
}

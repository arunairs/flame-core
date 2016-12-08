package cn.blinkmind.depot.server.repository.entity;

import cn.blinkmind.depot.server.bean.web.GeneralRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Transient;

import java.util.List;

/**
 * @author jiaan.zhang@oracle.com
 * @date 26/09/2016 2:40 PM
 */
public class Archive extends EntityBean implements Resource
{
	private String description;
	private List<Module> modules;
	private GeneralRequest request;

	@Override
	@Transient
	public Long getId()
	{
		return super.getId();
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public List<Module> getModules()
	{
		return modules;
	}

	public void setModules(List<Module> modules)
	{
		this.modules = modules;
	}

	public GeneralRequest getRequest()
	{
		return request;
	}

	@JsonIgnoreProperties(value = {"methods"})
	public void setRequest(GeneralRequest request)
	{
		this.request = request;
	}

	@Override
	public void cleanup(CRUD operation)
	{
		super.cleanup(operation);
	}

	@Override
	public String getScheme()
	{
		return this.request != null ? this.request.getScheme() : null;
	}

	@Override
	public String getUri()
	{
		return this.request != null ? this.request.getUri() : null;
	}

	@JsonIgnore
	@Override
	public Resource getParent()
	{
		return null;
	}
}
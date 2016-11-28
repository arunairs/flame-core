package cn.blinkmind.depot.server.bean.web;

/**
 * @author jiaan.zhang@oracle.com
 * @date 14/10/2016 3:50 PM
 */
public class SingleFieldRequestParameter implements RequestParameter
{
	private String name;
	private String value;
	private Boolean isRequired;
	private ParameterType parameterType;
	private String comment;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public Boolean getRequired()
	{
		return isRequired;
	}

	public void setRequired(Boolean required)
	{
		isRequired = required;
	}

	public ParameterType getParameterType()
	{
		return parameterType;
	}

	public void setParameterType(ParameterType parameterType)
	{
		this.parameterType = parameterType;
	}

	public String getComment()
	{
		return comment;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}
}

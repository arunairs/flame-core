package cn.blm.promise.server.bean;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author jiaan.zhang@oracle.com
 * @date 12/10/2016 12:03 AM
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class BodyWrapper
{
	Long id;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public static Builder builder()
	{
		return new Builder();
	}

	public static class Builder
	{
		Long id;

		public Builder id(Long id)
		{
			this.id = id;
			return this;
		}

		public BodyWrapper build()
		{
			BodyWrapper bodyWrapper = new BodyWrapper();
			bodyWrapper.setId(this.id);
			return bodyWrapper;
		}
	}
}

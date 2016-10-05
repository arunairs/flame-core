package cn.blm.promise.server.repository.entity;

/**
 * @author jiaan.zhang@oracle.com
 * @date 04/10/2016 11:29 PM
 */
public class Version
{
	private Integer major;
	private Integer minor;
	private Integer patch;

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

	public Version(Integer major, Integer minor, Integer patch)
	{
		this.major = major;
		this.minor = minor;
		this.patch = patch;
	}

	public Version()
	{
	}

	public static class Builder
	{
		private Integer major;
		private Integer minor;
		private Integer patch;

		public Builder major(Integer major)
		{
			this.major = major;
			return this;
		}

		public Builder minor(Integer minor)
		{
			this.minor = minor;
			return this;
		}

		public Builder patch(Integer patch)
		{
			this.patch = patch;
			return this;
		}

		public Version build()
		{
			return new Version(this.major, this.minor, this.patch);
		}
	}
}

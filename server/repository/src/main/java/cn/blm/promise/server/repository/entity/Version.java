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

	public Integer getMinor()
	{
		return minor;
	}

	public Integer getPatch()
	{
		return patch;
	}

	private Version()
	{
	}

	public Version(Integer major, Integer minor, Integer patch)
	{
		this.major = major;
		this.minor = minor;
		this.patch = patch;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Version version = (Version) o;

		if (!major.equals(version.major)) return false;
		if (!minor.equals(version.minor)) return false;
		return patch.equals(version.patch);

	}

	@Override
	public int hashCode()
	{
		int result = major.hashCode();
		result = 31 * result + minor.hashCode();
		result = 31 * result + patch.hashCode();
		return result;
	}
}

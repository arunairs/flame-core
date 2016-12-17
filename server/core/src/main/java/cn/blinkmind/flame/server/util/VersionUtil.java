package cn.blinkmind.flame.server.util;

import cn.blinkmind.flame.server.repository.entity.Version;

public class VersionUtil
{
	public static boolean isValid(Version version)
	{
		return version.getMajor() != null && version.getMinor() != null && version.getPatch() != null;
	}

	public static boolean isNotValid(Version version)
	{
		return !isValid(version);
	}
}

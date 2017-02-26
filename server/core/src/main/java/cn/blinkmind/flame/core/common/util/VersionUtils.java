package cn.blinkmind.flame.core.common.util;

import cn.blinkmind.flame.repository.model.Version;

public class VersionUtils
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

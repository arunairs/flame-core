package cn.blinkmind.depot.server.util;

import cn.blinkmind.depot.server.repository.entity.Version;

/**
 * @author jiaan.zhang@oracle.com
 * @date 19/10/2016 2:38 PM
 */
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

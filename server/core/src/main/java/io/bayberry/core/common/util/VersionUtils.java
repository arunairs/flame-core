package io.bayberry.core.common.util;

import io.bayberry.repository.entity.Version;

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

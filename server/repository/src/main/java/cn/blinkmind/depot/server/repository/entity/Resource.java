package cn.blinkmind.depot.server.repository.entity;

/**
 * @author jiaan.zhang@oracle.com
 * @date 14/11/2016 3:00 PM
 */
public interface Resource extends Locatable
{
	Resource getParent();
}

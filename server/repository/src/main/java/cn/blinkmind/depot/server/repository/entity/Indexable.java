package cn.blinkmind.depot.server.repository.entity;

import java.io.Serializable;

/**
 * @author jiaan.zhang@outlook.com
 * @date 19/10/2016 2:01 PM
 */
public interface Indexable<ID extends Serializable>
{
	ID getId();

	void setId(ID id);
}

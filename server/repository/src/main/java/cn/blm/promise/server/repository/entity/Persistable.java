package cn.blm.promise.server.repository.entity;

import java.io.Serializable;

/**
 * @author jiaan.zhang@oracle.com
 * @date 19/10/2016 2:01 PM
 */
public interface Persistable<ID extends Serializable>
{
	ID getId();

	void setId(ID id);
}

package cn.blm.promise.server.repository.domain;

import javax.persistence.Entity;

/**
 * @author jiaan.zhang@oracle.com
 * @date 26/09/2016 3:57 PM
 */
@Entity(name = "module")
public class ModuleEntity extends AbstractEntity
{
	private String name;
	private String url;
}

package cn.blm.promise.server.repository.entity;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author jiaan.zhang@oracle.com
 * @date 26/09/2016 3:57 PM
 */
@Document(collection = "modules")
public class ModuleEntity extends AbstractEntity
{
	private String name;
	private String url;
}

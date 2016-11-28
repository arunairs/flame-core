package cn.blinkmind.depot.server.repository.entity;

/**
 * @author jiaan.zhang@oracle.com
 * @date 28/11/2016 2:42 PM
 */
@org.springframework.data.mongodb.core.mapping.Document(collection = "releases")
public class Release extends EntityBean
{
	private Version version;
	private Archive archive;
}

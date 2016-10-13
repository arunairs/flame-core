package cn.blm.promise.server.repository.entity;

import cn.blm.promise.server.repository.enumeration.DocumentType;
import org.springframework.data.mongodb.core.index.CompoundIndex;

/**
 * @author jiaan.zhang@oracle.com
 * @date 26/09/2016 1:42 PM
 */
@org.springframework.data.mongodb.core.mapping.Document(collection = "documents")
@CompoundIndex(name = "unique_index", unique = true, def = "{'name':1,'documentType':1,'creatorId':1}")
public class Document extends BaseEntity
{
	private String name;
	private String description;
	private DocumentType documentType;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public DocumentType getDocumentType()
	{
		return documentType;
	}

	public void setDocumentType(DocumentType documentType)
	{
		this.documentType = documentType;
	}
}

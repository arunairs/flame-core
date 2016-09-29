package cn.blm.promise.repository.domain;

import cn.blm.promise.repository.enumeration.DocumentType;

import javax.persistence.Entity;

/**
 * @author jiaan.zhang@oracle.com
 * @date 26/09/2016 1:42 PM
 */
@Entity
public class DocumentEntity extends AbstractEntity
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

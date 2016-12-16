package cn.blinkmind.duck.server.repository.entity;

import org.springframework.data.mongodb.core.index.CompoundIndex;

@org.springframework.data.mongodb.core.mapping.Document(collection = "documents")
@CompoundIndex(name = "unique_index", unique = true, def = "{'name':1,'type':1,'creatorRef._id':1}")
public class Document extends EntityBean
{
	private String name;
	private String description;
	private DocumentType type;

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

	public DocumentType getType()
	{
		return type;
	}

	public void setType(DocumentType type)
	{
		this.type = type;
	}
}

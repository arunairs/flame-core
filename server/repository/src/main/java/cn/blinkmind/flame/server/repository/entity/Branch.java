package cn.blinkmind.flame.server.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.CompoundIndex;

@org.springframework.data.mongodb.core.mapping.Document(collection = "branches")
@CompoundIndex(name = "unique_index", unique = true, def = "{'name':1,'documentRef._id':1}")
public class Branch extends EntityBean
{
	private String name;
	private Ref<Long> documentRef;
	private Document document;
	private Archive archive;
	private Long version;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@JsonIgnore
	public Ref<Long> getDocumentRef()
	{
		return documentRef;
	}

	@JsonIgnore
	private void setDocumentRef(Ref<Long> documentRef)
	{
		this.documentRef = documentRef;
	}

	@Transient
	public Document getDocument()
	{
		return document;
	}

	public void setDocument(Document document)
	{
		this.document = document;
		if (this.document != null)
			setDocumentRef(new Ref<>(this.document));
		else setDocumentRef(null);
	}

	public Archive getArchive()
	{
		return archive;
	}

	public void setArchive(Archive archive)
	{
		this.archive = archive;
	}

	@org.springframework.data.annotation.Version
	public Long getVersion()
	{
		return version;
	}

	public void setVersion(Long version)
	{
		this.version = version;
	}
}

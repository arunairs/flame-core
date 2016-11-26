package cn.blinkmind.promise.server.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.CompoundIndex;

/**
 * @author jiaan.zhang@oracle.com
 * @date 26/09/2016 3:54 PM
 */
@org.springframework.data.mongodb.core.mapping.Document(collection = "branches")
@CompoundIndex(name = "unique_index", unique = true, def = "{'name':1,'documentRef._id':1}")
public class Branch extends BaseEntity
{
	private String name;
	private Ref<Long> documentRef;
	private Document document;
	private Archive archive;

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

	@JsonIgnore
	public void setArchive(Archive archive)
	{
		this.archive = archive;
	}
}

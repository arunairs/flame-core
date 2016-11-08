package cn.blinkmind.promise.server.repository.entity;

import cn.blinkmind.promise.server.bean.web.Request;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Transient;

import java.util.*;

/**
 * @author jiaan.zhang@oracle.com
 * @date 26/09/2016 2:40 PM
 */
@org.springframework.data.mongodb.core.mapping.Document(collection = "archives")
public class Archive extends BaseEntity implements Locatable
{
	public enum Status
	{
		OPEN, RELEASED
	}

	private Version version;
	private String description;
	private Branch branch;
	private Status status = Status.OPEN;
	private Ref<Long> documentRef;
	private Document document;
	private LinkedHashSet<Node> nodes;
	private LinkedHashSet<Module> modules;
	private Request request;

	public Version getVersion()
	{
		return version;
	}

	public void setVersion(Version version)
	{
		this.version = version;
	}

	@JsonIgnore
	public Ref<Long> getDocumentRef()
	{
		return documentRef;
	}

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

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Branch getBranch()
	{
		return branch;
	}

	public void setBranch(Branch branch)
	{
		this.branch = branch;
	}

	public Status getStatus()
	{
		return status;
	}

	public void setStatus(Status status)
	{
		this.status = status;
	}

	@JsonIgnore
	public Set<Node> getNodes()
	{
		if (nodes == null) nodes = new LinkedHashSet<>();
		return nodes;
	}

	public void setNodes(LinkedHashSet<Node> nodes)
	{
		this.nodes = nodes;
	}

	@Transient
	public LinkedHashSet<Module> getModules()
	{
		return modules;
	}

	public void setModules(LinkedHashSet<Module> modules)
	{
		this.modules = modules;
	}

	public Request getRequest()
	{
		return request;
	}

	public void setRequest(Request request)
	{
		this.request = request;
	}

	@Override
	public void clean()
	{
		super.clean();
		this.status = Status.OPEN;
	}

	@Override
	public String getScheme()
	{
		return this.request != null ? this.request.getScheme() : null;
	}

	@Override
	public String getUri()
	{
		return this.request != null ? this.request.getUri() : null;
	}
}

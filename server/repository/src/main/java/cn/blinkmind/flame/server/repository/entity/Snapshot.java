package cn.blinkmind.flame.server.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.CompoundIndex;

@org.springframework.data.mongodb.core.mapping.Document(collection = "snapshots")
@CompoundIndex(name = "unique_index", unique = true, def = "{'name':1,'branchRef._id':1,'creatorRef._id':1}")
public class Snapshot extends BasicEntity<Long> implements Commit<Archive>
{
    private String name;
    private Ref<Long> branchRef;
    private Branch branch;
    private Headers headers = new Headers();
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
    public Ref<Long> getBranchRef()
    {
        return branchRef;
    }

    private void setBranchRef(Ref<Long> branchRef)
    {
        this.branchRef = branchRef;
    }

    @Transient
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonIgnoreProperties(value = {"createdDate", "archive", "version"})
    public Branch getBranch()
    {
        return branch;
    }

    public void setBranch(Branch branch)
    {
        this.branch = branch;
        if (this.branch != null)
            setBranchRef(new Ref<>(this.branch));
        else setBranchRef(null);
    }

    @Override
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public Headers getHeaders()
    {
        return headers;
    }

    private void setHeaders(Headers headers)
    {
        this.headers = headers;
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public Archive getArchive()
    {
        return archive;
    }

    public void setArchive(Archive archive)
    {
        this.archive = archive == null ? new Archive() : archive;
    }

    @Override
    @JsonIgnore
    public Archive getPayload()
    {
        return this.archive;
    }
}

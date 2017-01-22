package cn.blinkmind.flame.server.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class BasicEntity<ID extends Serializable> implements Persistable<ID>
{
    private ID id;
    private Ref<ID> creatorRef;
    private Date createdDate;
    private Date updatedDate;
    private User creator;

    @Id
    @Override
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public ID getId()
    {
        return id;
    }

    @Override
    public void setId(ID id)
    {
        this.id = id;
    }

    @JsonIgnore
    public Ref<ID> getCreatorRef()
    {
        return creatorRef;
    }

    private void setCreatorRef(Ref<ID> creatorRef)
    {
        this.creatorRef = creatorRef;
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public Date getCreatedDate()
    {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate)
    {
        this.createdDate = createdDate;
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public Date getUpdatedDate()
    {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate)
    {
        this.updatedDate = updatedDate;
    }

    @Transient
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonIgnoreProperties({"id", "createdDate", "updatedDate"})
    public User getCreator()
    {
        return creator;
    }

    @SuppressWarnings("unchecked")
    public void setCreator(User creator)
    {
        this.creator = creator;
        if (this.creator != null)
            setCreatorRef(new Ref<>((Persistable<ID>) this.creator));
        else setCreatorRef(null);
    }
}

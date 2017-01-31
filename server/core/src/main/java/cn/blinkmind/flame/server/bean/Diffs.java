package cn.blinkmind.flame.server.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashSet;
import java.util.Set;

public class Diffs<E>
{
    private Set<E> addedCollection;
    private Set<E> modifiedCollection;
    private Set<E> removedCollection;

    @JsonProperty("added")
    public Set<E> getAddedCollection()
    {
        if (this.addedCollection == null)
            this.addedCollection = new LinkedHashSet<>();
        return this.addedCollection;
    }

    private void setAddedCollection(Set<E> addedCollection)
    {
        this.addedCollection = addedCollection;
    }

    @JsonProperty("modified")
    public Set<E> getModifiedCollection()
    {
        if (this.modifiedCollection == null)
            this.modifiedCollection = new LinkedHashSet<>();
        return this.modifiedCollection;
    }

    private void setModifiedCollection(Set<E> modifiedCollection)
    {
        this.modifiedCollection = modifiedCollection;
    }

    @JsonProperty("removed")
    public Set<E> getRemovedCollection()
    {
        if (this.removedCollection == null)
            this.removedCollection = new LinkedHashSet<>();
        return this.removedCollection;
    }

    private void setRemovedCollection(Set<E> removedCollection)
    {
        this.removedCollection = removedCollection;
    }
}

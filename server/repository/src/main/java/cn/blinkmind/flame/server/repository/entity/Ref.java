package cn.blinkmind.flame.server.repository.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Ref<ID extends Serializable> {
    private ID id;

    public Ref(Persistable<ID> persistable) {
        this.id = persistable.getId();
    }

    private Ref() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ref<?> ref = (Ref<?>) o;

        return id.equals(ref.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}

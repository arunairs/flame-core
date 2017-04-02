package io.bayberry.repository.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString(callSuper = true)
@Document(collection = "branches")
@CompoundIndex(name = "unique_index", unique = true, def = "{'name':1,'documentRef._id':1}")
public class Branch extends BaseModel<Long> implements Commit<Archive> {
    private String name;
    private Headers headers;
    private Archive archive;
    private Ref<Long> documentRef;

    @JsonProperty(value = "origin")
    private Ref<Long> originRef;

    @Override
    public Archive getPayload() {
        return this.archive;
    }

    public Long getOriginId() {
        return hasOrigin() ? this.getOriginRef().getId() : null;
    }

    public boolean hasOrigin() {
        return this.getOriginRef() != null && this.getOriginRef().getId() != null;
    }
}
package cn.blinkmind.flame.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.CompoundIndex;

@Getter
@Setter
@Document(collection = "snapshots")
@CompoundIndex(name = "unique_index", unique = true, def = "{'name':1,'branchRef._id':1,'creatorRef._id':1}")
public class Snapshot extends BaseModel<Long> implements Commit<Archive> {
    private String name;
    private Ref<Long> branchRef;
    private Headers headers;
    private Archive archive;

    @Override
    @JsonIgnore
    public Archive getPayload() {
        return this.archive;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}

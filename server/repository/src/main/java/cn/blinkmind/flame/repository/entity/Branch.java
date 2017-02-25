package cn.blinkmind.flame.repository.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "branches")
@CompoundIndex(name = "unique_index", unique = true, def = "{'name':1,'documentRef._id':1}")
public class Branch extends BaseEntity<Long> implements Commit<Archive> {
    private String name;
    private Headers headers;
    private Archive archive;
    private Ref<Long> documentRef;
    private Ref<Long> originRef;

    @Override
    public Archive getPayload() {
        return this.archive;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}

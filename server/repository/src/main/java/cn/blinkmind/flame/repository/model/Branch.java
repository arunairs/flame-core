package cn.blinkmind.flame.repository.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document(collection = "branches")
@CompoundIndex(name = "unique_index", unique = true, def = "{'name':1,'documentRef._id':1}")
public class Branch extends BaseModel<Long> implements Commit<Archive> {
    private String name;
    private Headers headers;
    private Archive archive;
    private Ref<Long> documentRef;
    private Ref<Long> originRef;

    @Override
    public Archive getPayload() {
        return this.archive;
    }
}

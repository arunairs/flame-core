package io.bayberry.repository.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString(callSuper = true)
@Document(collection = "drafts")
@CompoundIndex(name = "unique_index", unique = true, def = "{'branchRef._id':1,'creatorRef._id':1}")
public class Draft extends BaseEntity<Long>{
    private Archive archive;
    private Ref<Long> branchRef;
}

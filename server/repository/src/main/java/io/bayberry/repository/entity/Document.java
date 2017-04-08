package io.bayberry.repository.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.index.CompoundIndex;

@Getter
@Setter
@ToString(callSuper = true)
@org.springframework.data.mongodb.core.mapping.Document(collection = "documents")
@CompoundIndex(name = "unique_index", unique = true, def = "{'name':1,'type':1,'creatorRef._id':1}")
public class Document extends BaseEntity<Long> {
    private String name;
    private String description;
}

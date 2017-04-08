package io.bayberry.repository.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString(callSuper = true)
@Document(collection = "releases")
public class Release extends BaseEntity<Long> {
    private Version version;
    private Archive archive;
    private Ref<Long> branchRef;
}

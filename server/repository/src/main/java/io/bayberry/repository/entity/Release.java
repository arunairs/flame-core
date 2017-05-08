package io.bayberry.repository.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "releases")
public class Release extends BaseEntity {

    private Version version;
    private Archive archive;
    private Long branchId;
}

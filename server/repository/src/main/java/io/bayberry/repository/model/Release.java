package io.bayberry.repository.model;

import io.bayberry.common.bean.Version;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "releases")
public class Release extends BaseModel {

    private Version version;
    private Archive archive;
    private Long branchId;
}

package cn.blinkmind.flame.repository.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document(collection = "releases")
public class Release extends BaseModel<Long> {
    private Version version;
    private Archive archive;
    private Ref<Long> branchRef;
}

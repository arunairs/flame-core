package cn.blinkmind.flame.repository.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "releases")
public class Release extends BaseModel<Long> {
    private Version version;
    private Archive archive;
    private Ref<Long> branchRef;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}

package io.bayberry.repository.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Getter
@Setter
@ToString(callSuper = true)
@Document(collection = "commits")
public class Commit extends BaseEntity<Long> {
    private static final String COMMIT_ID = "id";
    private static final String COMMIT_VERSION = "version";
    private Headers headers;
    private Archive payload;
    private Ref<Long> branchRef;

    static boolean equals(Commit first, Commit second) {
        return Objects.equals(first.getCommitId(), second.getCommitId());
    }

    public Long getCommitVersion() {
        return getHeaders().getLong(COMMIT_VERSION);
    }

    public String getCommitId() {
        return getHeaders().getString(COMMIT_ID);
    }
}

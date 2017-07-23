package io.bayberry.flame.core.dto;

import io.bayberry.flame.core.repository.entity.Branch;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BranchResponse extends AbstractEntityResponse<Branch> {

    private String name;
    private Long documentId;

    public BranchResponse(Branch source) {
        super(source);
    }
}

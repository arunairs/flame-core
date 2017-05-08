package io.bayberry.core.dto;

import io.bayberry.common.util.BeanUtils;
import io.bayberry.repository.entity.Branch;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BranchResponse {

    private Long id;
    private String name;
    private Long documentId;

    public BranchResponse(Branch branch) {
        BeanUtils.copyProperties(branch, this);
    }
}

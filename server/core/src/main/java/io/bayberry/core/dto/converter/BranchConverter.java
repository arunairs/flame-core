package io.bayberry.core.dto.converter;

import io.bayberry.common.util.BeanUtils;
import io.bayberry.core.dto.BranchRequest;
import io.bayberry.repository.entity.Branch;
import org.springframework.stereotype.Component;

@Component
public class BranchConverter {

    public Branch convert(BranchRequest request) {
        Branch branch = new Branch();
        BeanUtils.copyProperties(request, branch);
        return branch;
    }
}

package io.bayberry.flame.core.dto.converter;

import io.bayberry.flame.common.util.BeanUtils;
import io.bayberry.flame.core.dto.BranchRequest;
import io.bayberry.flame.core.repository.entity.Branch;
import org.springframework.stereotype.Component;

@Component
public class BranchConverter {

    public Branch convert(BranchRequest request) {
        Branch branch = new Branch();
        BeanUtils.copyProperties(request, branch);
        return branch;
    }
}

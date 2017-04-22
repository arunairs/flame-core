package io.bayberry.core.dto.converter;

import io.bayberry.common.util.BeanUtils;
import io.bayberry.core.dto.BranchRequest;
import io.bayberry.repository.model.Branch;
import org.springframework.stereotype.Component;

@Component
public class BranchConverter {

    public Branch convert(BranchRequest request) {
        Branch branch = new Branch();
        BeanUtils.copy(request, branch);
        return branch;
    }
}

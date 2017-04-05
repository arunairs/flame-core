package io.bayberry.core.service.impl;

import io.bayberry.core.common.validation.Validator;
import io.bayberry.core.exception.Errors;
import io.bayberry.core.service.BranchService;
import io.bayberry.repository.BranchRepository;
import io.bayberry.repository.model.Branch;
import io.bayberry.repository.model.Ref;
import io.bayberry.repository.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static io.bayberry.core.common.validation.Matcher.blank;
import static io.bayberry.core.common.validation.Matcher.not;
import static io.bayberry.core.common.validation.Validator.orElseThrow;
import static io.bayberry.core.common.validation.Validator.validateThat;

@Service
public class BranchServiceImpl implements BranchService {
    private final BranchRepository branchRepository;

    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Override
    public Optional<Branch> get(Long id, User user) {
        return Optional.ofNullable(branchRepository.get(id));
    }

    @Override
    public Branch create(String name, Long documentId, User user) {
        Branch branch = new Branch();
        branch.setName(name);
        return this.create(branch, documentId, user);
    }

    @Override
    public Branch create(Branch branch, Long documentId, User user) {
        validateThat(branch.getName(), not(blank()), orElseThrow(() -> Errors.BRANCH_NAME_IS_BLANK)).
                and(not(branchRepository.exists(branch.getName(), documentId)),
                        orElseThrow(() -> Errors.RESOURCE_ALREADY_EXISTS));

        Branch output = new Branch();
        output.setName(branch.getName());
        output.setCreator(user);
        output.setDocumentRef(new Ref<>(documentId));
        if (branch.hasOrigin()) {
            Branch origin = this.get(branch.getOriginId(), user)
                    .orElseThrow(() -> Errors.BRANCH_ORIGIN_IS_NOT_FOUND);
            output.setOriginRef(new Ref<>(origin.getId()));
            output.setArchive(origin.getArchive());
            output.getHeader().putAll(origin.getHeader());
        }
        branchRepository.insert(output);
        return output;
    }

    @Override
    public void delete(Long id, User user) {
        branchRepository.delete(id);
    }

    @Override
    public Branch updateProfile(Long id, Branch branch, User user) {
        Validator.validateThat(branch.getName(), not(blank()), orElseThrow(Errors.BRANCH_NAME_IS_BLANK));

        Branch output = this.get(id, user).orElseThrow(() -> Errors.RESOURCE_NOT_FOUND);
        output.setName(branch.getName());
        branchRepository.update(output);
        return output;
    }
}

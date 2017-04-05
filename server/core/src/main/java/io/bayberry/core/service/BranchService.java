package io.bayberry.core.service;

import io.bayberry.repository.model.Branch;
import io.bayberry.repository.model.User;

import java.util.Optional;

public interface BranchService {

    Optional<Branch> get(Long id, User user);

    Branch create(String name, Long documentId, User user);

    Branch create(Branch branch, Long documentId, User user);

    void delete(Long id, User user);

    Branch updateProfile(Long id, Branch branch, User user);
}

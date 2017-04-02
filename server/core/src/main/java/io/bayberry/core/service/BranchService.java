package io.bayberry.core.service;

import io.bayberry.repository.model.Archive;
import io.bayberry.repository.model.Branch;
import io.bayberry.repository.model.Commit;
import io.bayberry.repository.model.User;

import java.util.Optional;

public interface BranchService {

    Optional<Branch> get(Long id, User user);

    Branch create(Long documentId, Branch branch, User creator);

    boolean exists(Long id, Long documentId);

    void delete(Long id, User user);

    Branch updateProfile(Long id, Branch branch, User user);

    Branch updateArchive(Branch branch, Commit<Archive> commit, User user);
}

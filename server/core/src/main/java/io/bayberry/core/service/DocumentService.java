package io.bayberry.core.service;

import io.bayberry.core.authentication.Auth;
import io.bayberry.repository.DocumentRepository;
import io.bayberry.repository.model.Branch;
import io.bayberry.repository.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final BranchService branchService;

    @Autowired
    public DocumentService(DocumentRepository documentRepository,
                           BranchService branchService) {
        this.documentRepository = documentRepository;
        this.branchService = branchService;
    }

    public Optional<Document> get(Long id, Auth auth) {
        return Optional.ofNullable(this.documentRepository.get(id));
    }

    public Document create(Document document, Auth auth) {
        document.setCreatorId(auth.getUserId());
        this.documentRepository.insert(document);
        this.createFirstBranch(document.getId(), auth);
        return document;
    }

    private void createFirstBranch(Long documentId, Auth auth) {
        Branch branch = new Branch();
        branch.setName("master");
        this.branchService.create(branch, documentId, auth);
    }
}

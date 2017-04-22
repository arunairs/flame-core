package io.bayberry.core.listener;

import io.bayberry.core.event.DocumentCreatedEvent;
import io.bayberry.core.service.BranchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DocumentEventListener {

    @Autowired
    private BranchService branchService;

    @EventListener
    public void handleDocumentCreatedEvent(DocumentCreatedEvent event) {
        branchService.createMasterBranch(event.getSource(), event.getAuth());
    }
}

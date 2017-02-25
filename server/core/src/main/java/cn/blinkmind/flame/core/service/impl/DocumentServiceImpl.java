package cn.blinkmind.flame.core.service.impl;

import cn.blinkmind.flame.core.common.util.Assert;
import cn.blinkmind.flame.common.util.BeanUtils;
import cn.blinkmind.flame.core.dto.DocumentDTO;
import cn.blinkmind.flame.core.dto.UserDTO;
import cn.blinkmind.flame.core.exception.Errors;
import cn.blinkmind.flame.repository.DocumentRepository;
import cn.blinkmind.flame.repository.entity.Document;
import cn.blinkmind.flame.repository.entity.Ref;
import cn.blinkmind.flame.core.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public DocumentDTO get(final Long id, final UserDTO userDTO) {
        Document document = this.documentRepository.get(id);
        if (document == null) {
            return null;
        }
        DocumentDTO documentDTO = new DocumentDTO();
        BeanUtils.copyProperties(documentDTO, document);
        return documentDTO;
    }

    @Override
    public Long create(final DocumentDTO documentDTO, final UserDTO userDTO) {
        Assert.isNotBlank(documentDTO.getName(), Errors.DOCUMENT_NAME_IS_BLANK);

        Document document = new Document();
        document.setName(documentDTO.getName());
        document.setDescription(documentDTO.getDescription());
        document.setCreatorRef(new Ref<>(userDTO.getId()));
        this.documentRepository.insert(document);
        return document.getId();
    }
}

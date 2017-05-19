package io.bayberry.core.dto.converter;

import io.bayberry.common.util.BeanUtils;
import io.bayberry.core.dto.DocumentRequest;
import io.bayberry.core.repository.entity.Document;
import org.springframework.stereotype.Component;

@Component
public class DocumentConverter {

    public Document convert(DocumentRequest request) {
        Document document = new Document();
        BeanUtils.copyProperties(request, document);
        return document;
    }
}

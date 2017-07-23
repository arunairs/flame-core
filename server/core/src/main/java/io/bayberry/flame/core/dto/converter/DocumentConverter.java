package io.bayberry.flame.core.dto.converter;

import io.bayberry.flame.common.util.BeanUtils;
import io.bayberry.flame.core.dto.DocumentRequest;
import io.bayberry.flame.core.repository.entity.Document;
import org.springframework.stereotype.Component;

@Component
public class DocumentConverter {

    public Document convert(DocumentRequest request) {
        Document document = new Document();
        BeanUtils.copyProperties(request, document);
        return document;
    }
}

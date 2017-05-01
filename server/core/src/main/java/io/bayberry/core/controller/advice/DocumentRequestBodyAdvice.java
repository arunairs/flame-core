package io.bayberry.core.controller.advice;

import io.bayberry.core.common.util.RequestUtils;
import io.bayberry.core.dto.DocumentRequest;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@ControllerAdvice
public class DocumentRequestBodyAdvice extends GenericRequestBodyAdvice<DocumentRequest> {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Override
    public DocumentRequest postProcess(DocumentRequest request, MethodParameter parameter) {
        Map<String, String> pathVariables = RequestUtils.getPathVariables(httpServletRequest);
        request.setId(MapUtils.getLong(pathVariables, "id"));
        return request;
    }
}

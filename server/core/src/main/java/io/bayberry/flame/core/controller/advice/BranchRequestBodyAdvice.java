package io.bayberry.flame.core.controller.advice;

import io.bayberry.flame.core.common.util.RequestUtils;
import io.bayberry.flame.core.dto.BranchRequest;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@ControllerAdvice
public class BranchRequestBodyAdvice extends GenericRequestBodyAdvice<BranchRequest> {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Override
    public BranchRequest postProcess(BranchRequest request, MethodParameter parameter) {
        Map<String, String> pathVariables = RequestUtils.getPathVariables(httpServletRequest);
        request.setId(MapUtils.getLong(pathVariables, "id"));
        request.setDocumentId(MapUtils.getLong(pathVariables, "documentId"));
        return request;
    }
}

package cn.blinkmind.flame.server.repository.entity;

import cn.blinkmind.flame.server.web.request.Request;
import cn.blinkmind.flame.server.web.request.Response;
import cn.blinkmind.flame.server.web.request.http.HttpRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.util.StringJoiner;

public interface Api extends ArchiveNode, NameNode, Persistable<Long> {

    Request getRequest();

    Response getResponse();
}

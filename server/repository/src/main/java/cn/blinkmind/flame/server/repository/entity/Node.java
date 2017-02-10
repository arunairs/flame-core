package cn.blinkmind.flame.server.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Transient;

public interface Node {

    @Transient
    @JsonIgnore
    Node getParent();
}

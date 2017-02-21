package cn.blinkmind.flame.repository.entity;

import org.springframework.data.annotation.Transient;

public interface Node {
    @Transient
    Node getParent();
}

package cn.blinkmind.flame.server.repository.entity;

import java.util.Collection;

public interface Node {
    Node getParent();

    Collection<? extends Node> getChildren();
}

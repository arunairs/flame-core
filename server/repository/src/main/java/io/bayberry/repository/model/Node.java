package io.bayberry.repository.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Node<ID extends Serializable> {
    private ID id;
    private NodeType type;
    private String path;
}

package io.bayberry.repository.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
public class Module extends Node {
    private List<Ref<Long>> moduleOrder;
    private List<Ref<Long>> apiOrder;
}

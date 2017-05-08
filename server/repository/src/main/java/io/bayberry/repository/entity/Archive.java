package io.bayberry.repository.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Archive {

    private String description;
    private List<Long> moduleOrders;
    private List<Module> modules;
    private List<Api> apis;
}

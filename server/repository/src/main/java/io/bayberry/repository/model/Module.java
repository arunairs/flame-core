package io.bayberry.repository.model;

import io.bayberry.common.protocol.Request;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
public class Module extends AbstractModule {
    private List<Module> modules;
    private List<AbstractApi> apis;
    private Request request;
}

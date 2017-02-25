package cn.blinkmind.flame.repository.entity;

import cn.blinkmind.flame.common.protocol.Request;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.List;

@Getter
@Setter
public class Module extends AbstractModule {
    private List<Module> modules;
    private List<AbstractApi> apis;
    private Request request;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}

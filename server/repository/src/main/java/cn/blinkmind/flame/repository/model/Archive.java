package cn.blinkmind.flame.repository.model;

import cn.blinkmind.flame.common.protocol.Request;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.List;

@Getter
@Setter
public class Archive extends AbstractArchive {
    private List<Module> modules;
    private Request request;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}

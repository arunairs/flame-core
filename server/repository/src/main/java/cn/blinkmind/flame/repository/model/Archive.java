package cn.blinkmind.flame.repository.model;

import cn.blinkmind.flame.common.protocol.Request;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Archive extends AbstractArchive {
    private List<Module> modules;
    private Request request;
}

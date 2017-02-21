package cn.blinkmind.flame.repository.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Archive extends AbstractArchiveNode {
    private List<Module> modules;

    @Override
    public Node getParent() {
        return null;
    }
}

package cn.blinkmind.flame.server.repository.entity;

import java.util.Collection;

public interface Module extends ArchiveNode, NameNode, Persistable<Long> {
    Collection<? extends Module> getModules();
    Collection<? extends Api> getApis();
}

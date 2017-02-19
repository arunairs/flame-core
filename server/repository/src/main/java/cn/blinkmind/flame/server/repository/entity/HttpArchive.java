package cn.blinkmind.flame.server.repository.entity;

import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.List;

@JsonTypeName(value = ArchiveType.Value.HTTP)
public class HttpArchive extends AbstractArchive{
    private List<HttpModule> modules;

    @Override
    public ArchiveType getArchiveType() {
        return ArchiveType.HTTP;
    }

    @Override
    public List<HttpModule> getModules() {
        return modules;
    }
}

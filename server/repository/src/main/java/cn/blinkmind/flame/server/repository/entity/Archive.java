package cn.blinkmind.flame.server.repository.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Collection;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "archiveType")
@JsonSubTypes({@JsonSubTypes.Type(value = HttpArchive.class)})
public interface Archive extends ArchiveNode{
    ArchiveType getArchiveType();
    Collection<? extends Module> getModules();
}

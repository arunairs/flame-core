package cn.blinkmind.flame.server.repository.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ArchiveType {
    HTTP(Value.HTTP);

    private String value;

    ArchiveType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static ArchiveType of(String value) {
        for (ArchiveType archiveType : ArchiveType.values()) {
            if (archiveType.getValue().equals(value))
                return archiveType;
        }
        return null;
    }

    public interface Value {
        String HTTP = "HTTP";
    }
}

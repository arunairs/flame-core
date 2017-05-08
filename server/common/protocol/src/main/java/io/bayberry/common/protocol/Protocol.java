package io.bayberry.common.protocol;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Protocol {
    HTTP(Names.HTTP),
    WEB_SOCKET(Names.WEB_SOCKET);

    @Getter
    private final String name;

    public interface Names {
        String HTTP = "HTTP";
        String WEB_SOCKET = "WEB_SOCKET";
    }
}

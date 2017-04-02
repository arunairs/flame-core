package io.bayberry.common.protocol;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Protocol {
    HTTP(Values.HTTP),
    WEB_SOCKET(Values.WEB_SOCKET);

    @Getter
    private final String value;

    public interface Values {
        String HTTP = "HTTP";
        String WEB_SOCKET = "WEB_SOCKET";
    }
}

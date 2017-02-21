package cn.blinkmind.flame.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Protocol {
    HTTP(Names.HTTP),
    WEB_SOCKET(Names.WEB_SOCKET);

    private final String name;

    public interface Names {
        String HTTP = "Http";
        String WEB_SOCKET = "WebSocket";
    }
}

/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.websocket;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebSocketEvent.java, v 0.1 2024‐09‐09 8:02 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum WebSocketEvent {

    SESSION_AUTH_REQUIRED("SESSION_AUTH_REQUIRED"),
    SESSION_AUTH_RESULT("SESSION_AUTH_RESULT"),
    PERFORM_AUTH_CLIENT("PERFORM_AUTH_CLIENT"),
    DATA_REQUEST("DATA_REQUEST"),
    DATA_RESULT("DATA_RESULT"),

    UNDEFINED("UNDEFINED"),
    ;

    private final String code;

    WebSocketEvent(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static WebSocketEvent getByCode(String code) {
        for (WebSocketEvent event : values()) {
            if (event.code.equals(code)) {
                return event;
            }
        }
        return UNDEFINED;
    }
}
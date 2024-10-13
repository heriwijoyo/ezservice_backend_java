/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.websocket.model;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: DataChannel.java, v 0.1 2024‐10‐13 11:17 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum DataChannel {

    OVERALL("OVERALL"),
    VOTER_BASE_AREA("VOTER_BASE_AREA"),

    ;

    private final String code;

    DataChannel(String code) {
        this.code = code;
    }

    public static DataChannel getByCode(String code) {
        for (DataChannel channel : values()) {
            if (channel.code.equals(code)) {
                return channel;
            }
        }
        return null;
    }
}
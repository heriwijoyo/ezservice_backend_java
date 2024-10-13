/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.websocket.model;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: DataTopic.java, v 0.1 2024‐10‐13 11:17 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum DataTopic {

    OVERALL("OVERALL"),
    DEMOGRAPHIC("DEMOGRAPHIC"),
    VOTER_BASE_AREA("VOTER_BASE_AREA"),

    NON_DATA_TOPIC("NON_DATA_TOPIC"),

    ;

    private final String code;

    DataTopic(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static DataTopic getByCode(String code) {
        for (DataTopic channel : values()) {
            if (channel.code.equals(code)) {
                return channel;
            }
        }
        return null;
    }
}
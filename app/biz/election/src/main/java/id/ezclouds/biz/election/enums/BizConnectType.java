/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.enums;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizConnectType.java, v 0.1 2024‐05‐14 1:46 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum BizConnectType {

    WHATSAPP("WHATSAPP")
    ;

    private final String code;

    BizConnectType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.biz;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMainFeature.java, v 0.1 2024‐09‐22 6:00 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum BizMainFeature {

    ELECTION("ELECTION"),
    COMMERCE("COMMERCE"),

    CORE("CORE"),

    ;

    private final String code;

    BizMainFeature(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
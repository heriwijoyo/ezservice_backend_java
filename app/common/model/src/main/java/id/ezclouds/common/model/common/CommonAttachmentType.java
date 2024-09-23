/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.common;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CommonAttachmentType.java, v 0.1 2024‐09‐23 10:47 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum CommonAttachmentType {

    MEMBER_CARD_ID("MEMBER_CARD_ID"),
    MEMBER_FAMILY_ID("MEMBER_FAMILY_ID"),

    VOTER_CANVASS_REPORT("VOTER_CANVASS_REPORT"),

    ;

    private final String code;

    CommonAttachmentType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
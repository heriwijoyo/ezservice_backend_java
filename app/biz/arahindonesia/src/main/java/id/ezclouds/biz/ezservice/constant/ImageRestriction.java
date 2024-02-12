/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.constant;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ImageRestriction.java, v 0.1 2024‐02‐13 1:05 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum  ImageRestriction {

    PRIVATE("PRIVATE"),
    PUBLIC("PUBLIC"),

    ;

    private final String code;

    ImageRestriction(String code) {
        this.code = code;
    }
}
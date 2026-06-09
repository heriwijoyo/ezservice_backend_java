/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.core.organization;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreOrgEnum.java, v 0.1 2024‐10‐02 12:55 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum CoreOrgEnum {

    RJL0("RJL0"),

    ;

    private final String code;

    CoreOrgEnum(String code) {
        this.code = code;
    }

    public static CoreOrgEnum getByCode(String code) {
        for (CoreOrgEnum coreOrgEnum : values()) {
            if (coreOrgEnum.code.equals(code)) {
                return coreOrgEnum;
            }
        }
        return null;
    }
}
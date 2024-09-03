/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.area;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreAreaLevel.java, v 0.1 2024‐02‐18 6:58 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum CoreAreaLevel {

    PROVINCE("PROVINCE"),
    REGENCY("REGENCY"),
    DISTRICT("DISTRICT"),
    VILLAGE("VILLAGE"),
    ;

    private final String code;

    CoreAreaLevel(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static CoreAreaLevel getByCode(String code) {
        for (CoreAreaLevel areaLevel : values()) {
            if (areaLevel.getCode().equals(code)) {
                return areaLevel;
            }
        }
        return null;
    }
}
/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.core.feature;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreFeature.java, v 0.1 2024‐09‐22 6:00 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum CoreFeature implements Feature {


    ;

    private final String code;
    private final String name;

    CoreFeature(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }
}
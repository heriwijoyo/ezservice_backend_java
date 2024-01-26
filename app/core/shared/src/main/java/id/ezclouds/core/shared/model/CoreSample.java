/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.model;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreSample.java, v 0.1 2024‐01‐27 3:15 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CoreSample {

    private String id;
    private String value;

    public CoreSample(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public String getValue() {
        return value;
    }
}
/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api.model.survey;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: SelectionOption.java, v 0.1 2024‐05‐10 3:03 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class SelectionOption {

    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
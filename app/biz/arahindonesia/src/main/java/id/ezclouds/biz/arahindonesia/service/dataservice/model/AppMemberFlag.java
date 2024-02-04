/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service.dataservice.model;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppMemberFlag.java, v 0.1 2024‐02‐04 6:57 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class AppMemberFlag {

    private String flagCode;
    private String flagValue;

    public AppMemberFlag(String flagCode, String flagValue) {
        this.flagCode = flagCode;
        this.flagValue = flagValue;
    }

    public String getFlagCode() {
        return flagCode;
    }
    public String getFlagValue() {
        return flagValue;
    }
}
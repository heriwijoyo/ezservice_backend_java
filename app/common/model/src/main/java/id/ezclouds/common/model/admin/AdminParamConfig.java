/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.admin;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AdminParamConfig.java, v 0.1 2024‐10‐09 12:05 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class AdminParamConfig {

    private List<String> asyncProcessNames;

    public List<String> getAsyncProcessNames() {
        return asyncProcessNames;
    }

    public void setAsyncProcessNames(List<String> asyncProcessNames) {
        this.asyncProcessNames = asyncProcessNames;
    }
}
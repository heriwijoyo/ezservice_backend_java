/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.model.profile;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: MemberProfile.java, v 0.1 2023‐12‐11 12:43 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class MemberProfile {

    private Map<String, String> appProfiles = new HashMap<>();

    public Map<String, String> getAppProfiles() {
        return appProfiles;
    }
}
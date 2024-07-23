/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.processor.shared;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ProcessorConstant.java, v 0.1 2024‐07‐24 5:23 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class ProcessorConstant {

    private static String[] rjlBlacklistSubOrgs = { "20100000", "2000011006" };

    public static final String ORG_ID_RJL = "RJL0";

    public static List<String> getBlacklistSubOrgs(String orgId) {
        List<String> list = new ArrayList<>();

        if (ORG_ID_RJL.equals(orgId)) {
            list.addAll(Arrays.asList(rjlBlacklistSubOrgs));
        }

        return list;
    }

}
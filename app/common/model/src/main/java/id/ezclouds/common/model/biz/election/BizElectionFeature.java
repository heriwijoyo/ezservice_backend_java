/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.biz.election;

import id.ezclouds.common.model.core.feature.Feature;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizElectionFeature.java, v 0.1 2024‐10‐13 5:24 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum BizElectionFeature implements Feature {

    VOTER_REGISTER("VOTER_REGISTER", "Voter Registration Feature"),

    ;

    private final String code;
    private final String name;


    BizElectionFeature(String code, String name) {
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

    public static BizElectionFeature getByCode(String code) {
        for (BizElectionFeature feature : values()) {
            if (feature.code.equals(code)) {
                return feature;
            }
        }
        return null;
    }
}
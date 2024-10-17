/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.biz.report;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizAccumulateMemberKey.java, v 0.1 2024‐10‐10 2:46 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum BizAccumulateMemberKey {

    VOTER_SUCCESS("VOTER_SUCCESS"),
    VOTER_INVALID("VOTER_INVALID"),
    VOTER_DUPLICATE("VOTER_DUPLICATE"),

    VOTER_CANVASS("VOTER_CANVASS"),

    SURVEY_SUCCESS("SURVEY_SUCCESS"),
    SURVEY_FAILED("SURVEY_FAILED"),

    ;

    private final String key;

    BizAccumulateMemberKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public static BizAccumulateMemberKey getByKey(String key) {
        for (BizAccumulateMemberKey accumulateMember : values()) {
            if (accumulateMember.key.equals(key)) {
                return accumulateMember;
            }
        }
        return null;
    }
}
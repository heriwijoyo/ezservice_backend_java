/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.broker.topic;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzCoreTopic.java, v 0.1 2024‐10‐02 12:38 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum EzCoreTopic {

    ELECTION_CANVASS_RECORD_ADD("ELECTION_CANVASS_RECORD_ADD"),
    ELECTION_VOTER_REGISTER("ELECTION_VOTER_REGISTER"),
    ELECTION_VOTER_REGISTER_INVALID("ELECTION_VOTER_REGISTER_INVALID"),
    ELECTION_QUICK_COUNT_SUBMIT("ELECTION_QUICK_COUNT_SUBMIT"),
    ELECTION_QUICK_COUNT_VERIFY("ELECTION_QUICK_COUNT_VERIFY"),

    CORE_MEMBER_REGISTER("CORE_MEMBER_REGISTER"),
    CORE_SUB_ORG_CREATE("CORE_SUB_ORG_CREATE"),

    BIZ_REPORT_OVERALL_UPDATE("BIZ_REPORT_OVERALL_UPDATE"),
    BIZ_REPORT_RECOVER_REGISTER_VOTER("BIZ_REPORT_RECOVER_REGISTER_VOTER"),

    ;

    private final String code;

    EzCoreTopic(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static EzCoreTopic getByCode(String code) {
        for (EzCoreTopic coreTopic : values()) {
            if (coreTopic.code.equals(code)) {
                return coreTopic;
            }
        }
        return null;
    }
}
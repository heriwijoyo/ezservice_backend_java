/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.constant;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: SurveyGroupQuery.java, v 0.1 2024‐11‐24 12:22 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum SurveyGroupQuery {

    BY_SUBMITTER("BY_SUBMITTER"),

    BY_RESPONDER_01("BY_RESPONDER_01"),
    BY_RESPONDER_02("BY_RESPONDER_02"),
    BY_RESPONDER_03("BY_RESPONDER_03"),
    BY_RESPONDER_04("BY_RESPONDER_04"),
    BY_RESPONDER_05("BY_RESPONDER_05"),
    BY_RESPONDER_06("BY_RESPONDER_06"),
    BY_RESPONDER_07("BY_RESPONDER_07"),
    BY_RESPONDER_08("BY_RESPONDER_08"),

    BY_RESPONSE_01("BY_RESPONSE_01"),
    BY_RESPONSE_02("BY_RESPONSE_02"),
    BY_RESPONSE_03("BY_RESPONSE_03"),
    BY_RESPONSE_04("BY_RESPONSE_04"),
    BY_RESPONSE_05("BY_RESPONSE_05"),

    ;

    private final String code;

    SurveyGroupQuery(String code) {
        this.code = code;
    }
}
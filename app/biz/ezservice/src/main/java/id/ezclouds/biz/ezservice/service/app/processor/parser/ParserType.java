/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.app.processor.parser;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ParserType.java, v 0.1 2024‐05‐10 1:38 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$ */
public enum ParserType {

    RJL_SURVEY_001("RJL_SURVEY_001"),
    UNKNOWN("UNKNOWN")
    ;

    private final String code;

    ParserType(String code) {
        this.code = code;
    }

    public static ParserType getByCode(String code) {
        for (ParserType parserType : values()) {
            if (parserType.code.equals(code)) {
                return parserType;
            }
        }
        return UNKNOWN;
    }
}
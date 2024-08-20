/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.process;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: SchedulerScene.java, v 0.1 2024‐07‐25 11:44 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$ */
public enum SchedulerScene {

    MINUTE("MINUTE"),
    RJL_DAILY_REPORT("RJL_DAILY_REPORT"),
    RJL_DAILY_SEND_SUB_ORG_REPORT("RJL_DAILY_SEND_SUB_ORG_REPORT"),
    RJL_DAILY_CHECK_NO_SUB_ORG("RJL_DAILY_CHECK_NO_SUB_ORG"),

    SAMPLE_SEQUENCE("SAMPLE_SEQUENCE"),
    SAMPLE_ASYNC("SAMPLE_ASYNC"),
    SAMPLE_DAL("SAMPLE_DAL"),
    UNKNOWN("UNKNOWN"),
    ;

    private final String code;

    SchedulerScene(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static SchedulerScene getByCode(String code) {
        for (SchedulerScene schedulerScene : values()) {
            if (schedulerScene.code.equals(code)) {
                return schedulerScene;
            }
        }
        return UNKNOWN;
    }
}
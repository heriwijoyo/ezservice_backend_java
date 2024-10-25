/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.model;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreSchedulerScene.java, v 0.1 2024‐07‐28 6:30 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum CoreSchedulerScene {

    REPORT_DAILY_RESET("REPORT_DAILY_RESET"),

    RJL_DAILY_REPORT_OVERALL("RJL_DAILY_REPORT_OVERALL"),
    RJL_HOURLY_MEMBER_TODAY("RJL_HOURLY_MEMBER_TODAY"),
    UNKNOWN("UNKNOWN"),
    ;
    private final String code;

    CoreSchedulerScene(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static CoreSchedulerScene getByCode(String code) {
        for (CoreSchedulerScene scene : values()) {
            if (scene.code.equals(code)) {
                return scene;
            }
        }
        return UNKNOWN;
    }
}
/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.enums;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizSchedulerScene.java, v 0.1 2024‐07‐18 12:51 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum BizSchedulerScene {

    MINUTE("MINUTE"),
    RJL_DAILY_REPORT("RJL_DAILY_REPORT"),
    RJL_DAILY_SEND_SUB_ORG_REPORT("RJL_DAILY_SEND_SUB_ORG_REPORT"),
    RJL_DAILY_CHECK_NO_SUB_ORG("RJL_DAILY_CHECK_NO_SUB_ORG"),

    SAMPLE_SEQUENCE("SAMPLE_SEQUENCE"),
    SAMPLE_ASYNC("SAMPLE_ASYNC"),
    UNKNOWN("UNKNOWN"),

    ;

    private final String code;

    BizSchedulerScene(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static BizSchedulerScene getByCode(String code) {
        for (BizSchedulerScene scene : values()) {
            if (scene.code.equals(code)) {
                return scene;
            }
        }
        return UNKNOWN;
    }
}
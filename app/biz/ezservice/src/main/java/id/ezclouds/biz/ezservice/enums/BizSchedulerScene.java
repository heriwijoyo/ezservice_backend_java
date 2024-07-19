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
    RJL_SEND_SUB_ORG_REPORT_DAILY("RJL_SEND_SUB_ORG_REPORT_DAILY"),
    CUSTOM_DAILY_REPORT("CUSTOM_DAILY_REPORT"),
    DAILY_CHECK_NO_SUB_ORG("DAILY_CHECK_NO_SUB_ORG"),
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
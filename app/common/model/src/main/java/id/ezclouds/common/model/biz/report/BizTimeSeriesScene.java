/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.biz.report;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizTimeSeriesScene.java, v 0.1 2024‐10‐25 8:37 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum BizTimeSeriesScene {

    CLUSTER("CLUSTER"),
    CORE_AREA("CORE_AREA"),

    ;

    private final String code;

    BizTimeSeriesScene(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static BizTimeSeriesScene getByCode(String code) {
        for (BizTimeSeriesScene seriesScene : values()) {
            if (seriesScene.code.equals(code)) {
                return seriesScene;
            }
        }
        return null;
    }
}
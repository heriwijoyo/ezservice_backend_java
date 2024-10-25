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

    VOTER_PROGRESS_BY_CLUSTER_DAILY("VOTER_PROGRESS_BY_CLUSTER_DAILY", BizTimeFrame.DAILY),
    VOTER_PROGRESS_BY_AREA_DAILY("VOTER_PROGRESS_BY_AREA_DAILY", BizTimeFrame.DAILY),

    ;

    private final String code;
    private final BizTimeFrame timeFrame;

    BizTimeSeriesScene(String code, BizTimeFrame timeFrame) {
        this.code = code;
        this.timeFrame = timeFrame;
    }

    public String getCode() {
        return code;
    }

    public BizTimeFrame getTimeFrame() {
        return timeFrame;
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
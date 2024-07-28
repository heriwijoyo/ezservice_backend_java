/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.report.converter;

import id.ezclouds.common.model.report.BizReportOverall;
import id.ezclouds.core.dal.report.dataobject.BizReportOverallDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportOverallConverter.java, v 0.1 2024‐07‐28 8:05 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizReportOverallConverter {

    public static BizReportOverallDO convert(BizReportOverall model) {
        BizReportOverallDO dataObject = new BizReportOverallDO();
        dataObject.setId(model.getId());
        dataObject.setOrgId(model.getOrgId());
        dataObject.setScene(model.getScene());
        dataObject.setKeyId(model.getKeyId());
        dataObject.setCount(model.getCount());
        return dataObject;
    }
}
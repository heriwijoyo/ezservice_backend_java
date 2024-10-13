/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.report.converter;

import id.ezclouds.common.model.biz.report.BizReportPage;
import id.ezclouds.common.model.biz.report.BizReportSection;
import id.ezclouds.common.model.converter.CommonDOModelConverter;
import id.ezclouds.core.dal.report.dataobject.BizReportPageDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportPageConverter.java, v 0.1 2024‐10‐13 6:31 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizReportPageConverter extends CommonDOModelConverter<BizReportPageDO, BizReportPage> {

    @Override
    protected BizReportPage safeConvertQuery(BizReportPageDO dataObject) {
        BizReportPage bizReportPage = new BizReportPage();
        bizReportPage.setReportPageId(dataObject.getReportPageId());
        bizReportPage.setOrgId(dataObject.getOrgId());
        bizReportPage.setSection(BizReportSection.getByCode(dataObject.getSection()));
        bizReportPage.setCode(dataObject.getCode());
        bizReportPage.setTitle(dataObject.getTitle());
        bizReportPage.setAuthType(dataObject.getAuthType());
        bizReportPage.setContent(dataObject.getContent());
        return bizReportPage;
    }

    @Override
    protected BizReportPageDO safeConvertStore(BizReportPage model) {
        return null;
    }
}
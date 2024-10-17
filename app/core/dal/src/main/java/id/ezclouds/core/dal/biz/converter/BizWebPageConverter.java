/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz.converter;

import id.ezclouds.common.model.biz.BizWebPage;
import id.ezclouds.common.model.converter.CommonDOModelConverter;
import id.ezclouds.core.dal.biz.dataobject.EzBizWebPageDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizWebPageConverter.java, v 0.1 2024‐08‐24 10:41 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizWebPageConverter extends CommonDOModelConverter<EzBizWebPageDO, BizWebPage> {

    @Override
    protected BizWebPage safeConvertQuery(EzBizWebPageDO dataObject) {
        BizWebPage webPage = new BizWebPage();
        webPage.setPageId(dataObject.getPageId());
        webPage.setOrgId(dataObject.getOrgId());
        webPage.setPath(dataObject.getPath());
        webPage.setSection(dataObject.getSection());
        webPage.setConfig(dataObject.getConfig());
        webPage.setContent(dataObject.getContent());
        webPage.setStatus(dataObject.getStatus());
        return webPage;
    }

    @Override
    protected EzBizWebPageDO safeConvertStore(BizWebPage model) {
        return null;
    }
}
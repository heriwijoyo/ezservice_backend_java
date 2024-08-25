/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.core.web.processor;

import id.ezclouds.common.facade.dal.admin.BizCommonTableDAO;
import id.ezclouds.common.model.biz.BizWebPageConfig;
import id.ezclouds.core.bifrost.core.web.model.WebPageContentType;
import id.ezclouds.core.bifrost.core.web.util.AssetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebPageProcessor.java, v 0.1 2024‐08‐24 6:59 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class WebPageProcessor {

    @Autowired
    private BizCommonTableDAO bizCommonTableDAO;

    public String process(String orgId, BizWebPageConfig pageConfig) throws Exception {
        WebPageContentType contentType = WebPageContentType.getByCode(pageConfig.getContentType());
        String layoutHTML = AssetUtil.parseAssetContent(contentType.getPageLayout());
        String pageHTML = layoutHTML.replace("PAGE_TITLE", pageConfig.getPageTitle());




        return pageHTML;
    }
}
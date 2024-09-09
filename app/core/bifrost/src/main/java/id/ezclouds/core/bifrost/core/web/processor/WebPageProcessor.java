/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.core.web.processor;

import id.ezclouds.common.model.biz.BizWebPageConfig;
import id.ezclouds.core.bifrost.core.web.component.WebViewHTMLContentComposer;
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
    private WebViewHTMLContentComposer webViewHTMLContentComposer;

    public String process(String sessionId, String orgId, String pageId, BizWebPageConfig pageConfig, String content) throws Exception {
        WebPageContentType contentType = WebPageContentType.getByCode(pageConfig.getContentType());
        String htmlLayout = AssetUtil.parseAssetContent(contentType.getPageLayout());
        String htmlContent = webViewHTMLContentComposer.composeHTMLContent(orgId, pageId, contentType, content);

        String pageHTML = htmlLayout
                .replace("PAGE_TITLE", pageConfig.getPageTitle())
                .replace("PAGE_CONTENT", htmlContent)
                .replace("PAGE_SESSION_ID", sessionId);

        return pageHTML;
    }
}
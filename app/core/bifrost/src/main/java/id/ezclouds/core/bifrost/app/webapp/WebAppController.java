/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.webapp;

import id.ezclouds.biz.ezservice.service.core.BizCacheKey;
import id.ezclouds.common.util.StringUtil;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebAppController.java, v 0.1 2024‐04‐27 9:35 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Controller
public class WebAppController {

    private static final String ASSET_INCLUDE_LAYOUT = "webapp/include/layout.incl";
    private static final String ASSET_INCLUDE_HEADER = "webapp/include/header.incl";
    private static final String ASSET_INCLUDE_NAVIGATION = "webapp/include/navigation.incl";

    @GetMapping(value = "/webapp/videocard.htm")
    private void webAppVideoCard(HttpServletResponse servletResponse) {
        renderCachedWebApp(getVideoCardContent(), servletResponse);
    }

    @GetMapping(value = "/webapp/profile.htm")
    private void webAppProfile(HttpServletResponse servletResponse) {
        renderCachedWebApp(getProfileContent(), servletResponse);
    }

    @GetMapping(value = "/webapp/whatsapp.htm")
    private void webWhatsapp(HttpServletResponse servletResponse) {
        renderCachedWebApp(getWhatsappContent(), servletResponse);
    }

    @GetMapping(value = "/webapp/documents.htm")
    private void webDocuments(HttpServletResponse servletResponse) {
        renderCachedWebApp(getDocumentsContent(), servletResponse);
    }

    @GetMapping(value = "/webapp/config.htm")
    private void webSpecialProcess(HttpServletResponse servletResponse) {
        renderCachedWebApp(getWebAppContent(WebAppPage.CONFIG), servletResponse);
    }

    @Cacheable(value = BizCacheKey.WEBAPP_VIDEO_CARD)
    public String getVideoCardContent() {
        return getWebAppContent(WebAppPage.VIDEO_CARD);
    }

    @Cacheable(value = BizCacheKey.WEBAPP_PROFILE)
    public String getProfileContent() {
        return getWebAppContent(WebAppPage.PROFILE);
    }

    @Cacheable(value = BizCacheKey.WEBAPP_WHATSAPP)
    public String getWhatsappContent() {
        return getWebAppContent(WebAppPage.WHATSAPP);
    }

    @Cacheable(value = BizCacheKey.WEBAPP_DOCUMENTS)
    public String getDocumentsContent() {
        return getWebAppContent(WebAppPage.DOCUMENTS);
    }

    private String getWebAppContent(WebAppPage webAppPage) {
        try {
            String layoutContent = readHtmlContent(ASSET_INCLUDE_LAYOUT);
            String headerContent = readHtmlContent(ASSET_INCLUDE_HEADER);
            String navigationContent = readHtmlContent(ASSET_INCLUDE_NAVIGATION);
            String pageContent = readHtmlContent(webAppPage.getAssetFile());

            String htmlContent = layoutContent
                    .replace("INCLUDE_HEADER", headerContent)
                    .replace("INCLUDE_NAVIGATION", navigationContent)
                    .replace("INCLUDE_PAGE_CONTENT", pageContent);
            return htmlContent;
        } catch (IOException e) {
            return StringUtil.EMPTY;
        }
    }

    private void renderCachedWebApp(String htmlContent, HttpServletResponse servletResponse) {
        if (StringUtil.EMPTY.equals(htmlContent)) {
            servletResponse.setStatus(HttpStatus.NOT_FOUND.value());
            servletResponse.setContentType("text/html;charset=UTF-8");
            htmlContent = "Not Found";
        } else {
            servletResponse.setStatus(HttpStatus.OK.value());
        }

        try {
            servletResponse.getWriter().write(htmlContent);
            servletResponse.getWriter().flush();
        } catch (IOException e) {
            servletResponse.setStatus(HttpStatus.NOT_FOUND.value());
        }
    }

    private String readHtmlContent(String assetFile) throws IOException {
        Resource resource = new ClassPathResource(assetFile);
        return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
    }
}
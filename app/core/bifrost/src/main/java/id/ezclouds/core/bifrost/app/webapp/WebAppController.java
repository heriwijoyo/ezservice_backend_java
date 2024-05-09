/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.webapp;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebAppController.java, v 0.1 2024‐04‐27 9:35 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Controller
public class WebAppController {

    private static final String ASSET_INCLUDE_LAYOUT = "classpath:webapp/include/layout.incl";
    private static final String ASSET_INCLUDE_HEADER = "classpath:webapp/include/header.incl";
    private static final String ASSET_INCLUDE_NAVIGATION = "classpath:webapp/include/navigation.incl";

    @GetMapping(value = "/webapp/videocard.htm")
    private void webAppVideoCard(HttpServletResponse servletResponse) {
        renderWebApp(WebAppPage.VIDEO_CARD, servletResponse);
    }

    @GetMapping(value = "/webapp/profile.htm")
    private void webAppProfile(HttpServletResponse servletResponse) {
        renderWebApp(WebAppPage.PROFILE, servletResponse);
    }

    @GetMapping(value = "/assets/go.htm")
    private void assetGo(HttpServletResponse servletResponse) {
        writePageNotFound(servletResponse);
    }

    private void renderWebApp(WebAppPage webAppPage, HttpServletResponse servletResponse) {
        try {
            String layoutContent = readHtmlContent(ASSET_INCLUDE_LAYOUT);
            String headerContent = readHtmlContent(ASSET_INCLUDE_HEADER);
            String navigationContent = readHtmlContent(ASSET_INCLUDE_NAVIGATION);
            String pageContent = readHtmlContent(webAppPage.getAssetFile());

            String htmlContent = layoutContent
                    .replace("INCLUDE_HEADER", headerContent)
                    .replace("INCLUDE_NAVIGATION", navigationContent)
                    .replace("INCLUDE_PAGE_CONTENT", pageContent);

            servletResponse.getWriter().write(htmlContent);
            servletResponse.getWriter().flush();
        } catch (IOException e) {
            writePageNotFound(servletResponse);
        }
    }

    private String readHtmlContent(String assetFile) throws IOException {
        File file = ResourceUtils.getFile(assetFile);
        return new String(Files.readAllBytes(file.toPath()));
    }

    private void writePageNotFound(HttpServletResponse response) {
        response.setStatus(HttpStatus.NOT_FOUND.value());
    }
}
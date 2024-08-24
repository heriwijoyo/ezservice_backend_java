/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.webpage;

import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.core.bifrost.core.model.WebPagePath;
import id.ezclouds.core.bifrost.core.model.WebPageRequest;
import id.ezclouds.core.bifrost.core.model.WebPageSection;
import id.ezclouds.core.bifrost.core.template.WebPageControllerTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebPageController.java, v 0.1 2024‐08‐21 9:06 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Controller
public class WebPageController {

    @GetMapping(path = {"/pages", "/pages/{path}/{section}/{pageId}/{session}"})
    private void getWebPage(
            HttpServletResponse response,
            @PathVariable(name = "path", required = false) String path,
            @PathVariable(name = "section", required = false) String section,
            @PathVariable(name = "pageId", required = false) String pageId,
            @PathVariable(name = "session", required = false) String session) {

        WebPageRequest request = new WebPageRequest();
        request.setPageId(pageId);
        request.setPath(path);
        request.setSection(section);
        request.setSession(session);

        WebPageControllerTemplate.execute(request, new WebPageControllerTemplate.Handler() {
            @Override
            public void checkRequest(WebPageRequest request) {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getPageId(), EzErrorCode.ILLEGAL_PARAM);

                WebPagePath pagePath = WebPagePath.getByCode(request.getPath());
                WebPageSection pageSection = WebPageSection.getByCode(request.getSection());
                AssertUtil.isNotTrue(pagePath == WebPagePath.UNKNOWN, EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.isNotTrue(pageSection == WebPageSection.UNKNOWN, EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public String processWebContent(WebPageRequest request) {
                String htmlContent =  "<html><head><title>CROT</title></head><body><h4>WANZENG MEN..!!!</h4></body></html>";

                return htmlContent;
            }

            @Override
            public HttpServletResponse getServletResponse() {
                return response;
            }
        });
    }
}
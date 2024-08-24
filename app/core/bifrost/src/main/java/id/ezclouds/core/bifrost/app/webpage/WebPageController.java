/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.webpage;

import id.ezclouds.common.facade.auth.AuthAdminService;
import id.ezclouds.common.facade.dal.biz.BizWebPageDAO;
import id.ezclouds.common.facade.integration.BizObjectMapperService;
import id.ezclouds.common.model.biz.BizWebPage;
import id.ezclouds.common.model.biz.BizWebPageConfig;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.core.bifrost.core.model.WebPageAuthType;
import id.ezclouds.core.bifrost.core.model.WebPagePath;
import id.ezclouds.core.bifrost.core.model.WebPageRequest;
import id.ezclouds.core.bifrost.core.model.WebPageSection;
import id.ezclouds.core.bifrost.core.template.WebPageControllerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private BizWebPageDAO bizWebPageDAO;

    @Autowired
    private BizObjectMapperService bizObjectMapperService;

    @Autowired
    private AuthAdminService authAdminService;

    @GetMapping(path = {"/pages", "/pages/{path}/{section}/{pageId}/{sessionId}"})
    private void getWebPage(
            HttpServletResponse response,
            @PathVariable(name = "path", required = false) String path,
            @PathVariable(name = "section", required = false) String section,
            @PathVariable(name = "pageId", required = false) String pageId,
            @PathVariable(name = "sessionId", required = false) String sessionId) {

        WebPageRequest request = new WebPageRequest();
        request.setPageId(pageId);
        request.setPath(path);
        request.setSection(section);
        request.setSessionId(sessionId);

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
                BizWebPage bizWebPage = bizWebPageDAO.getWebPage(request.getPageId());
                AssertUtil.notNull(bizWebPage, EzErrorCode.WEB_BIZ_PAGE_NOT_FOUND);

                BizWebPageConfig pageConfig = bizObjectMapperService
                        .parseJson(bizWebPage.getConfig(), BizWebPageConfig.class);
                WebPageAuthType pageAuthType = WebPageAuthType.getByCode(pageConfig.getAuthType());

                if (pageAuthType == WebPageAuthType.PUBLIC_SESSION) {
                    authAdminService.authorizeWebPublicSession(request.getSessionId());
                }


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
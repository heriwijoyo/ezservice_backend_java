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
import id.ezclouds.core.bifrost.core.web.model.WebPageAuthType;
import id.ezclouds.core.bifrost.core.web.model.WebPageRequest;
import id.ezclouds.core.bifrost.core.web.processor.WebPageProcessor;
import id.ezclouds.core.bifrost.core.web.WebPageControllerTemplate;
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

    @Autowired
    private WebPageProcessor webPageProcessor;

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
                AssertUtil.notBlank(request.getPath(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getSection(), EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public String processWebContent(WebPageRequest request) throws Exception {
                BizWebPage bizWebPage = bizWebPageDAO.getWebPage(request.getPageId());
                AssertUtil.notNull(bizWebPage, EzErrorCode.WEB_BIZ_PAGE_NOT_FOUND);
                AssertUtil.equals(request.getPath(), bizWebPage.getPath(), EzErrorCode.WEB_BIZ_PAGE_NOT_FOUND);
                AssertUtil.equals(request.getSection(), bizWebPage.getSection(), EzErrorCode.WEB_BIZ_PAGE_NOT_FOUND);

                BizWebPageConfig pageConfig = bizObjectMapperService
                        .parseJson(bizWebPage.getConfig(), BizWebPageConfig.class);
                WebPageAuthType pageAuthType = WebPageAuthType.getByCode(pageConfig.getAuthType());

                if (pageAuthType == WebPageAuthType.PUBLIC_SESSION) {
                    authAdminService.authorizeWebPublicSession(request.getSessionId());
                }

                return webPageProcessor
                        .process(bizWebPage.getOrgId(), bizWebPage.getPageId(), pageConfig, bizWebPage.getContent());
            }

            @Override
            public HttpServletResponse getServletResponse() {
                return response;
            }
        });
    }
}
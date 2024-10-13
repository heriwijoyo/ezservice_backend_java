/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.web.biz;

import id.ezclouds.common.facade.auth.AuthAdminService;
import id.ezclouds.common.facade.dal.biz.BizPageLayoutDAO;
import id.ezclouds.common.facade.dal.biz.report.BizReportPageDAO;
import id.ezclouds.common.model.auth.AuthSession;
import id.ezclouds.common.model.biz.report.BizReportPage;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.core.bifrost.core.web.model.WebPageAuthType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportPageController.java, v 0.1 2024‐10‐13 6:40 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Controller
public class BizReportPageController {

    @Autowired
    private BizReportPageDAO bizReportPageDAO;

    @Autowired
    private AuthAdminService authAdminService;

    @Autowired
    private BizPageLayoutDAO bizPageLayoutDAO;

    @GetMapping(value = "/biz/report/{section}/{code}/{sessionId}")
    private void getBizReportPage(
            @PathVariable("section") String section,
            @PathVariable("code") String code,
            @PathVariable("sessionId") String sessionId,
            HttpServletResponse response) {

        response.setContentType("text/html;charset=UTF-8");

        try {
            AssertUtil.notBlank(section, EzErrorCode.ILLEGAL_PARAM);
            AssertUtil.notBlank(code, EzErrorCode.ILLEGAL_PARAM);

            BizReportPage reportPage = bizReportPageDAO.getReportPage(section, code);
            AssertUtil.notNull(reportPage, EzErrorCode.DATA_NOT_FOUND);

            WebPageAuthType authType = WebPageAuthType.getByCode(reportPage.getAuthType());
            AssertUtil.notNull(authType, EzErrorCode.SYSTEM_ERROR);
            if (authType == WebPageAuthType.PUBLIC_SESSION) {
                AssertUtil.notBlank(sessionId, EzErrorCode.ILLEGAL_PARAM);
                AuthSession authSession = authAdminService.authorizeWebPublicSession(sessionId);
                AssertUtil.equals(authSession.getOrgId(), reportPage.getOrgId(), EzErrorCode.UNAUTHORIZED);
            }

            String layout = bizPageLayoutDAO.getContent(reportPage.getLayoutCode());
            String pageContent = reportPage
                    .getContent()
                    .replace("CONTENT_TITLE", reportPage.getContentTitle());

            String htmlContent = layout
                    .replace("PAGE_TITLE", reportPage.getPageTitle())
                    .replace("INCLUDE_PAGE_CONTENT", pageContent)
                    .replace("PAGE_SESSION_ID", sessionId);

            response.setStatus(HttpStatus.OK.value());
            response.getWriter().write(htmlContent);
            response.getWriter().flush();

        } catch (Exception e) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
    }
}
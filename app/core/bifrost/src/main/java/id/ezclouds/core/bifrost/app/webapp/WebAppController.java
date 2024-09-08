/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.webapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.ezclouds.biz.ezservice.service.apibiz.OldBizMemberService;
import id.ezclouds.biz.ezservice.service.core.BizCacheKey;
import id.ezclouds.common.facade.biz.BizReportService;
import id.ezclouds.common.model.report.BizMainReport;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.facade.BeanFacadeUtil;
import id.ezclouds.common.util.logger.CommonLoggerConstant;
import id.ezclouds.common.util.logger.DigestLog;
import id.ezclouds.common.model.auth.AuthAdminSession;
import id.ezclouds.core.auth.service.CoreAuthService;
import id.ezclouds.core.bifrost.app.api.digestlog.EmptyDigestLog;
import id.ezclouds.core.bifrost.app.web.event.WebEvent;
import id.ezclouds.core.bifrost.core.SpringContextConfig;
import id.ezclouds.common.util.context.EzAppContextHolder;
import id.ezclouds.core.bifrost.core.web.component.WebAppForm;
import id.ezclouds.core.bifrost.core.web.component.render.WebAppFormRenderer;
import id.ezclouds.core.bifrost.core.web.component.render.WebComponentRenderer;
import id.ezclouds.core.bifrost.core.web.component.factory.CommonTableFormFactory;
import id.ezclouds.core.shared.util.DigestLogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebAppController.java, v 0.1 2024‐04‐27 9:35 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Controller
public class WebAppController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonLoggerConstant.ADMIN_WEB_CONTROLLER);

    private static final String ASSET_INCLUDE_LAYOUT = "webapp/include/layout.incl";
    private static final String ASSET_INCLUDE_HEADER = "webapp/include/header.incl";
    private static final String ASSET_INCLUDE_NAVIGATION = "webapp/include/navigation.incl";

    @Autowired
    private CoreAuthService coreAuthService;

    @Autowired
    private BizReportService bizReportService;

    @GetMapping(value = "/webapp/home.htm")
    private void webAppHome(HttpServletResponse servletResponse) {
        renderCachedWebApp(getHomeContent(), servletResponse);
    }

    @GetMapping(value = "/webapp/appgallery.htm")
    private void webAppGallery(HttpServletResponse servletResponse) {
        renderCachedWebApp(getGalleryContent(), servletResponse);
    }

    @GetMapping(value = "/webapp/news.htm")
    private void webAppNews(HttpServletResponse servletResponse) {
        renderCachedWebApp(getNewsContent(), servletResponse);
    }

    @GetMapping(value = "/webapp/newsAdd.htm")
    private void webAppNewsAdd(HttpServletResponse servletResponse) {
        renderCachedWebApp(getNewsAddContent(), servletResponse);
    }

    @GetMapping(value = "/webapp/newsUpdate.htm")
    private void webAppNewsUpdate(HttpServletResponse servletResponse) {
        renderCachedWebApp(getNewsUpdateContent(), servletResponse);
    }

    @GetMapping(value = "/webapp/events.htm")
    private void webAppEvent(HttpServletResponse servletResponse) {
        renderCachedWebApp(getEventContent(), servletResponse);
    }

    @GetMapping(value = "/webapp/eventAdd.htm")
    private void webAppEventAdd(HttpServletResponse servletResponse) {
        renderCachedWebApp(getEventAddContent(), servletResponse);
    }

    @GetMapping(value = "/webapp/eventUpdate.htm")
    private void webAppEventUpdate(HttpServletResponse servletResponse) {
        renderCachedWebApp(getEventUpdateContent(), servletResponse);
    }

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

    @GetMapping(value = "/webapp/subOrganizations.htm")
    private void webSubOrganizations(HttpServletResponse servletResponse) {
        renderCachedWebApp(getSubOrganizationContent(), servletResponse);
    }

    @GetMapping(value = "/webapp/members.htm")
    private void webMembers(HttpServletResponse servletResponse) {
        renderCachedWebApp(getMembersContent(), servletResponse);
    }

    @GetMapping(value = "/webapp/memberAdd.htm")
    private void webMemberAdd(HttpServletResponse servletResponse) {
        renderCachedWebApp(getMemberAddContent(), servletResponse);
    }

    @GetMapping(value = "/webapp/memberDetail.htm")
    private void webMemberDetail(HttpServletResponse servletResponse) {
        renderCachedWebApp(getMemberDetailContent(), servletResponse);
    }

    @GetMapping(value = "/webapp/dataUpload.htm")
    private void dataUpload(HttpServletResponse servletResponse) {
        renderCachedWebApp(getDataUploadContent(), servletResponse);
    }

    @GetMapping(value = "/webapp/organization.htm")
    private void webAppOrganization(HttpServletResponse servletResponse) {
        renderCachedWebApp(getOrganizationContent(), servletResponse);
    }

    @GetMapping(value = "/webapp/addOrganization.htm")
    private void webAppOrganizationAdd(HttpServletResponse servletResponse) {
        renderCachedWebApp(getOrganizationAddContent(), servletResponse);
    }

    @GetMapping(value = "/webapp/organizationDetail.htm")
    private void webAppOrganizationDetail(HttpServletResponse servletResponse) {
        renderCachedWebApp(getOrganizationDetailContent(), servletResponse);
    }

    @GetMapping(value = "/webapp/config.htm")
    private void webConfig(HttpServletResponse servletResponse) {
        EzAppContextHolder.init(WebEvent.WEB_PAGE_ADMIN_CONFIG);
        boolean success = renderCachedWebApp(getWebAppContent(WebAppPage.CONFIG), servletResponse);
        DigestLogUtil.logWebDigest(LOGGER, getDigestLog(success));
    }

    @GetMapping(value = "/webapp/specialProcess.htm")
    private void webSpecialProcess(HttpServletResponse servletResponse) {
        EzAppContextHolder.init(WebEvent.WEB_PAGE_ADMIN_SPECIAL_PROCESS);
        boolean success = renderCachedWebApp(getWebAppContent(WebAppPage.SPECIAL_PROCESS), servletResponse);
        DigestLogUtil.logWebDigest(LOGGER, getDigestLog(success));
    }

    @GetMapping(value = "/webapp/addMember.htm")
    private void webAddMember(HttpServletResponse servletResponse) {
        EzAppContextHolder.init(WebEvent.WEB_PAGE_ADMIN_ADD_MEMBER);
        boolean success = renderCachedWebApp(getWebAppContent(WebAppPage.ADD_MEMBER), servletResponse);
        DigestLogUtil.logWebDigest(LOGGER, getDigestLog(success));
    }

    @GetMapping(value = "/webapp/commonTables.htm")
    private void webCommonTables(HttpServletResponse servletResponse) {
        renderCachedWebApp(getCommonTablesContent(), servletResponse);
    }

    @GetMapping(value = "/webapp/commonTableAdd.htm")
    private void webCommonTableAdd(HttpServletResponse servletResponse) {
        renderCachedWebApp(getCommonTableAddContent(), servletResponse);
    }

    @GetMapping(value = "/webapp/commonTableDetail.htm")
    private void webCommonTableDetail(HttpServletResponse servletResponse) {
        renderCachedWebApp(getCommonTableDetailContent(), servletResponse);
    }

    @GetMapping(value = "/webapp/data/{orgCode}/{sessionId}")
    private void webDataPage(
            @PathVariable("orgCode") String orgCode,
            @PathVariable("sessionId") String sessionId,
            HttpServletResponse servletResponse) {
        EzAppContextHolder.init(WebEvent.WEB_PAGE_ADMIN_PUBLIC_DATA_APP);
        boolean renderSuccess;
        try {
            AssertUtil.notBlank(orgCode, EzErrorCode.ILLEGAL_PARAM);
            AssertUtil.notBlank(sessionId, EzErrorCode.ILLEGAL_PARAM);

            AuthAdminSession session = SpringContextConfig
                    .getBean(CoreAuthService.class)
                    .adminAuthWebSessionId(sessionId);
            AssertUtil.isTrue(orgCode.equals(session.getOrgCode()), EzErrorCode.SESSION_INVALID);

            String htmlLayout = getReportPublicContent(WebAppPage.DATA_PUBLIC_LIMITED.getAssetFile());
            String htmlContent = htmlLayout
                    .replace("PAGE_TITLE", "Data Aplikasi")
                    .replace("ORG_CODE", orgCode)
                    .replace("INCLUDE_SESSION_ID", sessionId);
            renderSuccess = renderCachedWebApp(htmlContent, servletResponse);
        } catch (Exception e) {
            e.printStackTrace();
            renderSuccess = renderCachedWebApp(null, servletResponse);
        }
        DigestLogUtil.logWebDigest(LOGGER, getDigestLog(renderSuccess));
    }

    @GetMapping(value = "/application/report/{orgCode}/{sessionId}")
    private void webReportPage(
            @PathVariable("orgCode") String orgCode,
            @PathVariable("sessionId") String sessionId,
            HttpServletResponse servletResponse) {
        EzAppContextHolder.init(WebEvent.WEB_PAGE_ADMIN_PUBLIC_REPORT);
        boolean renderSuccess;
        try {
            AssertUtil.notBlank(orgCode, EzErrorCode.ILLEGAL_PARAM);
            AssertUtil.notBlank(sessionId, EzErrorCode.ILLEGAL_PARAM);

            AuthAdminSession session = SpringContextConfig
                    .getBean(CoreAuthService.class)
                    .adminAuthWebSessionId(sessionId);
            AssertUtil.isTrue(orgCode.equals(session.getOrgCode()), EzErrorCode.SESSION_INVALID);

            String jsReloadScript = "207".equals(orgCode) ? "setTimeout(function(){window.location.reload(1);},60000);" : "";

            String htmlLayout = getReportPublicContent(WebAppPage.REPORT_PUBLIC_LIMITED.getAssetFile());
            String htmlContent = htmlLayout
                    .replace("HTML_TITLE_PAGE", "Report Center")
                    .replace("INNER_PAGE_TITLE", "Report Center")
                    .replace("JS_RELOAD_SCRIPT", jsReloadScript);

            BizMainReport mainReport = BeanFacadeUtil
                    .getBean(BizReportService.class)
                    .getMainReport(session.getOrgId());

            Map<String, String> webDataMap = WebAppDataMapper.getDataMap(mainReport);
            for (Map.Entry<String, String> webDataMapEntry : webDataMap.entrySet()) {
                htmlContent = htmlContent
                        .replace(webDataMapEntry.getKey(), webDataMapEntry.getValue());
            }

            renderSuccess = renderCachedWebApp(htmlContent, servletResponse);
        } catch (Exception e) {
            e.printStackTrace();
            renderSuccess = renderCachedWebApp(null, servletResponse);
        }
        DigestLogUtil.logWebDigest(LOGGER, getDigestLog(renderSuccess));
    }

    @GetMapping(value = "/webapp/import/{orgCode}/{sessionId}")
    private void webDataImportPage(
            @PathVariable("orgCode") String orgCode,
            @PathVariable("sessionId") String sessionId,
            HttpServletResponse servletResponse) {
        EzAppContextHolder.init(WebEvent.WEB_PAGE_ADMIN_PUBLIC_REPORT);
        boolean renderSuccess;
        try {
            AssertUtil.notBlank(orgCode, EzErrorCode.ILLEGAL_PARAM);
            AssertUtil.notBlank(sessionId, EzErrorCode.ILLEGAL_PARAM);

            AuthAdminSession session = SpringContextConfig
                    .getBean(CoreAuthService.class)
                    .adminAuthWebSessionId(sessionId);
            AssertUtil.isTrue(orgCode.equals(session.getOrgCode()), EzErrorCode.SESSION_INVALID);

            String htmlLayout = getReportPublicContent(WebAppPage.DATA_PUBLIC_LIMITED.getAssetFile());
            String htmlContent = htmlLayout
                    .replace("PAGE_TITLE", "Data Google Sheet")
                    .replace("ORG_CODE", orgCode)
                    .replace("INCLUDE_SESSION_ID", sessionId);
            renderSuccess = renderCachedWebApp(htmlContent, servletResponse);
        } catch (Exception e) {
            e.printStackTrace();
            renderSuccess = renderCachedWebApp(null, servletResponse);
        }
        DigestLogUtil.logWebDigest(LOGGER, getDigestLog(renderSuccess));
    }

    @GetMapping(value = "/webapp/data/{orgCode}/app/{sessionId}.json")
    private void webDataJson(
            @PathVariable("orgCode") String orgCode,
            @PathVariable("sessionId") String sessionId,
            HttpServletResponse servletResponse) {
        EzAppContextHolder.init(WebEvent.WEB_PAGE_ADMIN_PUBLIC_DATA_APP);

        boolean success;

        try {
            AssertUtil.notBlank(orgCode, EzErrorCode.ILLEGAL_PARAM);
            AssertUtil.notBlank(sessionId, EzErrorCode.ILLEGAL_PARAM);

            AuthAdminSession session = SpringContextConfig
                    .getBean(CoreAuthService.class)
                    .adminAuthWebSessionId(sessionId);
            AssertUtil.isTrue(orgCode.equals(session.getOrgCode()), EzErrorCode.SESSION_INVALID);

            List<List<String>> jsonData = SpringContextConfig
                    .getBean(OldBizMemberService.class)
                    .getAllMemberData(session.getOrgId());

            success = renderJsonData(new ObjectMapper().writeValueAsString(jsonData), servletResponse);
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
        }
        DigestLogUtil.logWebDigest(LOGGER, getDigestLog(success));
    }

    @GetMapping(value = "/webapp/import/{orgCode}/app/{sessionId}.json")
    private void webImportJson(
            @PathVariable("orgCode") String orgCode,
            @PathVariable("sessionId") String sessionId,
            HttpServletResponse servletResponse) {
        EzAppContextHolder.init(WebEvent.WEB_PAGE_ADMIN_PUBLIC_DATA_APP);

        boolean success;

        try {
            AssertUtil.notBlank(orgCode, EzErrorCode.ILLEGAL_PARAM);
            AssertUtil.notBlank(sessionId, EzErrorCode.ILLEGAL_PARAM);

            AuthAdminSession session = SpringContextConfig
                    .getBean(CoreAuthService.class)
                    .adminAuthWebSessionId(sessionId);
            AssertUtil.isTrue(orgCode.equals(session.getOrgCode()), EzErrorCode.SESSION_INVALID);

            List<List<String>> jsonData = SpringContextConfig
                    .getBean(OldBizMemberService.class)
                    .getAllImportData(session.getOrgId());

            success = renderJsonData(new ObjectMapper().writeValueAsString(jsonData), servletResponse);
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
        }
        DigestLogUtil.logWebDigest(LOGGER, getDigestLog(success));
    }

    @Cacheable(value = BizCacheKey.WEBAPP_HOME)
    public String getHomeContent() {
        return getWebAppContent(WebAppPage.HOME);
    }

    @Cacheable(value = BizCacheKey.WEBAPP_GALLERY)
    public String getGalleryContent() {
        return getWebAppContent(WebAppPage.GALLERY);
    }

    @Cacheable(value = BizCacheKey.WEBAPP_NEWS)
    public String getNewsContent() {
        return getWebAppContent(WebAppPage.NEWS);
    }

    @Cacheable(value = BizCacheKey.WEBAPP_NEWS_ADD)
    public String getNewsAddContent() {
        return getWebAppContent(WebAppPage.NEWS_ADD);
    }

    @Cacheable(value = BizCacheKey.WEBAPP_NEWS_UPDATE)
    public String getNewsUpdateContent() {
        return getWebAppContent(WebAppPage.NEWS_UPDATE);
    }

    @Cacheable(value = BizCacheKey.WEBAPP_EVENT)
    public String getEventContent() {
        return getWebAppContent(WebAppPage.EVENT);
    }

    @Cacheable(value = BizCacheKey.WEBAPP_EVENT_ADD)
    public String getEventAddContent() {
        return getWebAppContent(WebAppPage.EVENT_ADD);
    }

    @Cacheable(value = BizCacheKey.WEBAPP_EVENT_UPDATE)
    public String getEventUpdateContent() {
        return getWebAppContent(WebAppPage.EVENT_UPDATE);
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

    @Cacheable(value = BizCacheKey.WEBAPP_SUB_ORGANIZATION)
    public String getSubOrganizationContent() {
        return getWebAppContent(WebAppPage.SUB_ORGANIZATIONS);
    }

    @Cacheable(value = BizCacheKey.WEBAPP_MEMBERS)
    public String getMembersContent() {
        return getWebAppContent(WebAppPage.MEMBERS);
    }

    @Cacheable(value = BizCacheKey.WEBAPP_MEMBER_ADD)
    public String getMemberAddContent() {
        return getWebAppContent(WebAppPage.MEMBER_ADD);
    }

    @Cacheable(value = BizCacheKey.WEBAPP_MEMBER_DETAIL)
    public String getMemberDetailContent() {
        return getWebAppContent(WebAppPage.MEMBER_DETAIL);
    }

    @Cacheable(value = BizCacheKey.WEBAPP_DATA_UPLOAD)
    public String getDataUploadContent() {
        return getWebAppContent(WebAppPage.DATA_UPLOAD);
    }

    @Cacheable(value = BizCacheKey.WEBAPP_ORGANIZATION)
    public String getOrganizationContent() {
        return getWebAppContent(WebAppPage.ORGANIZATION);
    }

    @Cacheable(value = BizCacheKey.WEBAPP_ORGANIZATION_ADD)
    public String getOrganizationAddContent() {
        return getWebAppContent(WebAppPage.ORGANIZATION_ADD);
    }

    @Cacheable(value = BizCacheKey.WEBAPP_ORGANIZATION_DETAIL)
    public String getOrganizationDetailContent() {
        return getWebAppContent(WebAppPage.ORGANIZATION_DETAIL);
    }

    @Cacheable(value = BizCacheKey.WEBAPP_TABLES)
    public String getCommonTablesContent() {
        return getWebAppContent(WebAppPage.COMMON_TABLES);
    }

    @Cacheable(value = BizCacheKey.WEBAPP_TABLE_ADD)
    public String getCommonTableAddContent() {
        String webAppContent = getWebAppContent(WebAppPage.COMMON_TABLE_ADD);
        WebAppForm webAppForm = new CommonTableFormFactory().create();
        return webAppContent
                .replace("INCLUDE_DYNAMIC_FORM", WebAppFormRenderer.renderForm(webAppForm))
                .replace("INCLUDE_SMART_FORM_CLIENT", WebAppFormRenderer.renderSmartFormClient(webAppForm));
    }

    @Cacheable(value = BizCacheKey.WEBAPP_TABLE_DETAIL)
    public String getCommonTableDetailContent() {
        String webAppContent = getWebAppContent(WebAppPage.COMMON_TABLE_DETAIL);
        CommonTableFormFactory tableFormFactory = new CommonTableFormFactory();
        tableFormFactory.setDetail(true);
        WebAppForm webAppForm = tableFormFactory.create();
        return webAppContent
                .replace("INCLUDE_DYNAMIC_FORM", WebAppFormRenderer.renderForm(webAppForm))
                .replace("INCLUDE_SMART_FORM_CLIENT", WebAppFormRenderer.renderSmartFormClientDetail(webAppForm));
    }

    @Cacheable(value = BizCacheKey.WEBAPP_REPORT_PUBLIC)
    public String getReportPublicContent(String assetFile) {
        try {
            return readHtmlContent(assetFile);
        } catch (IOException e) {
            return StringUtil.EMPTY;
        }
    }

    private String getWebAppContent(WebAppPage webAppPage) {
        try {
            String layoutContent = readHtmlContent(ASSET_INCLUDE_LAYOUT);
            String headerContent = readHtmlContent(ASSET_INCLUDE_HEADER);
            String navigationContent = readHtmlContent(ASSET_INCLUDE_NAVIGATION);
            String pageContent = readHtmlContent(webAppPage.getAssetFile());
            String searchComponent = WebComponentRenderer.getListSearchComponent(webAppPage);
            if (StringUtil.isNotBlank(searchComponent)) {
                pageContent = pageContent.replace("INCLUDE_SEARCH_COMPONENT", searchComponent);
            }

            String htmlContent = layoutContent
                    .replace("INCLUDE_HEADER", headerContent)
                    .replace("INCLUDE_NAVIGATION", navigationContent)
                    .replace("INCLUDE_PAGE_CONTENT", pageContent);
            return htmlContent;
        } catch (IOException e) {
            return StringUtil.EMPTY;
        }
    }

    private boolean renderCachedWebApp(String htmlContent, HttpServletResponse servletResponse) {
        boolean success = false;
        if (StringUtil.isBlank(htmlContent)) {
            servletResponse.setStatus(HttpStatus.NOT_FOUND.value());
            servletResponse.setContentType("text/html;charset=UTF-8");
            htmlContent = "Nothing Here";
        } else {
            servletResponse.setStatus(HttpStatus.OK.value());
            success = true;
        }

        try {
            servletResponse.getWriter().write(htmlContent);
            servletResponse.getWriter().flush();
        } catch (IOException e) {
            servletResponse.setStatus(HttpStatus.NOT_FOUND.value());
            success = false;
        }
        return success;
    }

    private boolean renderJsonData(String jsonData, HttpServletResponse servletResponse) {
        boolean success;
        try {
            servletResponse.setContentType("application/json");
            servletResponse.setStatus(HttpStatus.OK.value());
            servletResponse.getWriter().write(jsonData);
            servletResponse.getWriter().flush();
            success = true;
        } catch (IOException e) {
            servletResponse.setStatus(HttpStatus.NOT_FOUND.value());
            success = false;
        }
        return success;
    }

    private String readHtmlContent(String assetFile) throws IOException {
        Resource resource = new ClassPathResource(assetFile);
        return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
    }

    private DigestLog getDigestLog(boolean success) {
        return new EmptyDigestLog(success, "");
    }
}
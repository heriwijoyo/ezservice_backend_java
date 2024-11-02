/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.web.biz;

import id.ezclouds.common.facade.auth.AuthAdminService;
import id.ezclouds.common.facade.dal.biz.BizPageLayoutDAO;
import id.ezclouds.common.facade.dal.biz.election.BizVoterDAO;
import id.ezclouds.common.facade.dal.biz.election.BizVoterInvalidDAO;
import id.ezclouds.common.facade.dal.biz.report.BizReportPageDAO;
import id.ezclouds.common.facade.dal.member.BizMemberBackOfficeDAO;
import id.ezclouds.common.facade.dal.organization.SubOrganizationDAO;
import id.ezclouds.common.facade.integration.BizObjectMapperService;
import id.ezclouds.common.model.auth.AuthSession;
import id.ezclouds.common.model.biz.election.BizVoter;
import id.ezclouds.common.model.biz.election.BizVoterInvalid;
import id.ezclouds.common.model.biz.report.BizReportPage;
import id.ezclouds.common.model.core.organization.SubOrganization;
import id.ezclouds.common.model.member.MemberBackOffice;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.facade.BeanFacadeUtil;
import id.ezclouds.core.bifrost.core.config.WebAppConfig;
import id.ezclouds.core.bifrost.core.constant.WebConstant;
import id.ezclouds.core.bifrost.core.web.model.WebPageAuthType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

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

    @Autowired
    private BizVoterDAO bizVoterDAO;

    @Autowired
    private BizVoterInvalidDAO bizVoterInvalidDAO;

    @Autowired
    private BizObjectMapperService bizObjectMapperService;

    @Autowired
    private SubOrganizationDAO subOrganizationDAO;

    @Autowired
    private BizMemberBackOfficeDAO bizMemberBackOfficeDAO;

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

    @GetMapping(value = "/biz/data/voter/clusters/{sessionId}")
    private void getVoterByCluster(@PathVariable("sessionId") String sessionId, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");

        try {
            AssertUtil.notBlank(sessionId, EzErrorCode.SYSTEM_ERROR);
            AuthSession authSession = authAdminService.authorizeWebPublicSession(sessionId);
            List<SubOrganization> subOrganizations = subOrganizationDAO.getActiveSubOrg(authSession.getOrgId());

            StringBuilder stringBuilder = new StringBuilder();
            int number = 1;
            for (SubOrganization subOrganization : subOrganizations) {
                stringBuilder.append("<tr>");
                stringBuilder.append("<td>")
                        .append(number)
                        .append("</td>");
                stringBuilder
                        .append("<td>")
                        .append(subOrganization.getName())
                        .append("</td>");
                stringBuilder
                        .append("<td>")
                        .append("<a href=\"../cluster/"+ subOrganization.getSubOrgId() +"/"+ sessionId +"\">Lihat Detail</a>")
                        .append("</td>");
                stringBuilder.append("</tr>");
                number++;
            }

            String assetPath = "biz/data/voterClusters.htm";
            String htmlContent = getHtmlContent(assetPath)
                    .replace("INCLUDE_CONTENT", stringBuilder.toString());

            response.setStatus(HttpStatus.OK.value());
            response.getWriter().write(htmlContent);
            response.getWriter().flush();
        } catch (Exception e) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
    }

    @GetMapping(value = "/biz/data/voter/cluster/{clusterId}/{sessionId}")
    private void getVoterClusterMembers(
            @PathVariable("clusterId") String clusterId,
            @PathVariable("sessionId") String sessionId,
            HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");

        try {
            AssertUtil.notBlank(clusterId, EzErrorCode.SYSTEM_ERROR);
            AssertUtil.notBlank(sessionId, EzErrorCode.SYSTEM_ERROR);
            AuthSession authSession = authAdminService.authorizeWebPublicSession(sessionId);

            SubOrganization subOrganization = subOrganizationDAO.getById(clusterId);
            AssertUtil.notNull(subOrganization, EzErrorCode.SYSTEM_ERROR);

            Date expiredToken = DateUtil.getDateAfterMins(new Date(), 10);
            String tokenId = sessionId + expiredToken.getTime();

            List<MemberBackOffice> members = bizMemberBackOfficeDAO.getBySubOrgId(authSession.getOrgId(), clusterId);

            StringBuilder stringBuilder = new StringBuilder();
            int number = 1;
            for (MemberBackOffice member : members) {
                stringBuilder.append("<tr>");
                stringBuilder.append("<td>")
                        .append(number)
                        .append("</td>");
                stringBuilder
                        .append("<td>")
                        .append(member.getName())
                        .append("</td>");
                stringBuilder
                        .append("<td>")
                        .append("<a href=\"../../member/"+ member.getMemberId() +"/valid/"+ tokenId +"\">Download Data</a><br>(Data Valid)")
                        .append("</td>");
                stringBuilder
                        .append("<td>")
                        .append("<a href=\"../../member/"+ member.getMemberId() +"/invalid/"+ tokenId +"\">Download Data</a><br>(Data Invalid)")
                        .append("</td>");
                stringBuilder.append("</tr>");
                number++;
            }

            String assetPath = "biz/data/voterCluster.htm";
            String htmlContent = getHtmlContent(assetPath)
                    .replace("INCLUDE_CLUSTER_NAME", subOrganization.getName())
                    .replace("INCLUDE_CONTENT", stringBuilder.toString());

            response.setStatus(HttpStatus.OK.value());
            response.getWriter().write(htmlContent);
            response.getWriter().flush();
        } catch (Exception e) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
    }

    @GetMapping(value = "/biz/data/voter/member/{memberId}/{status}/{sessionToken}")
    private void getVoterByMembers(
            @PathVariable("memberId") String memberId,
            @PathVariable("status") String status,
            @PathVariable("sessionToken") String sessionToken,
            HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");

        try {
            AssertUtil.notBlank(memberId, EzErrorCode.SYSTEM_ERROR);
            AssertUtil.notBlank(status, EzErrorCode.SYSTEM_ERROR);
            AssertUtil.isTrue(status.equals("valid") || status.equals("invalid"), EzErrorCode.SYSTEM_ERROR);
            AssertUtil.notBlank(sessionToken, EzErrorCode.SYSTEM_ERROR);
            AssertUtil.isTrue(sessionToken.length() > 32, EzErrorCode.SYSTEM_ERROR);

            String sessionId = StringUtil.leftSubstring(sessionToken, 32);
            String expiryTime = sessionToken.substring(32);
            Date expiryDate = new Date(Long.parseLong(expiryTime));

            AssertUtil.isTrue(expiryDate.after(new Date()), EzErrorCode.SYSTEM_ERROR);
            AuthSession authSession = authAdminService.authorizeWebPublicSession(sessionId);

            MemberBackOffice member = bizMemberBackOfficeDAO.getMemberDetail(memberId);
            AssertUtil.notNull(member, EzErrorCode.SYSTEM_ERROR);

            if (status.equals("valid")) {
                List<BizVoter> bizVoters = bizVoterDAO
                        .getVoterByReferrer(authSession.getOrgId(), member.getMemberId());
            } else {
                List<BizVoterInvalid> bizVoterInvalids = bizVoterInvalidDAO
                        .getByReferrerId(authSession.getOrgId(), member.getMemberId());
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
    }

    @GetMapping(value = "/biz/data/voter/pollstation/{districtId}/{villageId}/{pollStation}/{sessionId}")
    private void getBizDataVoter(
            @PathVariable("districtId") String districtId,
            @PathVariable("villageId") String villageId,
            @PathVariable("pollStation") Integer pollStation,
            HttpServletResponse response) {

        response.setContentType("text/html;charset=UTF-8");

        try {
            String pollId = pollStation < 10 ? "0"+ pollStation : String.valueOf(pollStation);
            List<BizVoter> voters =  bizVoterDAO.getVoterDataPollStation(districtId, villageId, pollId);

            String layout = bizPageLayoutDAO.getContent("BIZ_DATA_VOTER");
            String htmlContent = layout
                    .replace("PAGE_CONTENT", bizObjectMapperService.toJson(voters));

            response.setStatus(HttpStatus.OK.value());
            response.getWriter().write(htmlContent);
            response.getWriter().flush();

        } catch (Exception e) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
    }

    private String getHtmlContent(String assetBizPath) throws IOException {
        WebAppConfig webConfig = BeanFacadeUtil.getBean(WebAppConfig.class);
        if (WebConstant.DEV.equals(webConfig.getWebReleaseMode())) {
            Path webAppSourcePath = Paths.get(webConfig.getWebResourceDir(), assetBizPath);
            return Files.readString(webAppSourcePath);
        }
        return StreamUtils.copyToString(new ClassPathResource(assetBizPath).getInputStream(), StandardCharsets.UTF_8);
    }
}
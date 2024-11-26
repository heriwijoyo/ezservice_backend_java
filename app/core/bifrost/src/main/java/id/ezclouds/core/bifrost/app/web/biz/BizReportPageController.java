/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.web.biz;

import id.ezclouds.common.facade.auth.AuthAdminService;
import id.ezclouds.common.facade.dal.biz.AppCommonDataSurveyDAO;
import id.ezclouds.common.facade.dal.biz.BizPageLayoutDAO;
import id.ezclouds.common.facade.dal.biz.BizSurveyTableDAO;
import id.ezclouds.common.facade.dal.biz.election.BizVoterDAO;
import id.ezclouds.common.facade.dal.biz.election.BizVoterInvalidDAO;
import id.ezclouds.common.facade.dal.biz.report.BizReportPageDAO;
import id.ezclouds.common.facade.dal.member.BizMemberBackOfficeDAO;
import id.ezclouds.common.facade.dal.organization.SubOrganizationDAO;
import id.ezclouds.common.facade.file.CoreFileService;
import id.ezclouds.common.facade.integration.BizObjectMapperService;
import id.ezclouds.common.model.auth.AuthSession;
import id.ezclouds.common.model.biz.election.BizVoter;
import id.ezclouds.common.model.biz.election.BizVoterInvalid;
import id.ezclouds.common.model.biz.report.BizReportPage;
import id.ezclouds.common.model.biz.survey.AppCommonDataSurvey;
import id.ezclouds.common.model.biz.survey.BizSurveyTable;
import id.ezclouds.common.model.core.organization.SubOrganization;
import id.ezclouds.common.model.file.ClusterFileResolver;
import id.ezclouds.common.model.file.PublicFileResolver;
import id.ezclouds.common.model.member.MemberBackOffice;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.facade.BeanFacadeUtil;
import id.ezclouds.core.bifrost.core.config.WebAppConfig;
import id.ezclouds.core.bifrost.core.constant.WebConstant;
import id.ezclouds.core.bifrost.core.web.model.WebPageAuthType;
import org.dhatim.fastexcel.Workbook;
import org.dhatim.fastexcel.Worksheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

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

    @Autowired
    private CoreFileService coreFileService;

    @Autowired
    private BizSurveyTableDAO bizSurveyTableDAO;

    @Autowired
    private AppCommonDataSurveyDAO appCommonDataSurveyDAO;

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
                    .replace("PAGE_CODE", code)
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
                        .append("<a href=\"../../member/"+ member.getMemberId() +"/"+ tokenId +"\">Download Data (XLS)</a>")
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

    @GetMapping(value = "/biz/data/voter/member/{memberId}/{sessionToken}")
    private void getVoterByMembers(
            @PathVariable("memberId") String memberId,
            @PathVariable("sessionToken") String sessionToken,
            HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");

        try {
            AssertUtil.notBlank(memberId, EzErrorCode.SYSTEM_ERROR);
            AssertUtil.notBlank(sessionToken, EzErrorCode.SYSTEM_ERROR);
            AssertUtil.isTrue(sessionToken.length() > 32, EzErrorCode.SYSTEM_ERROR);

            String sessionId = StringUtil.leftSubstring(sessionToken, 32);
            String expiryTime = sessionToken.substring(32);
            Date expiryDate = new Date(Long.parseLong(expiryTime));

            AssertUtil.isTrue(expiryDate.after(new Date()), EzErrorCode.SYSTEM_ERROR);
            AuthSession authSession = authAdminService.authorizeWebPublicSession(sessionId);

            MemberBackOffice member = bizMemberBackOfficeDAO.getMemberDetail(memberId);
            AssertUtil.notNull(member, EzErrorCode.SYSTEM_ERROR);
            SubOrganization subOrganization = subOrganizationDAO.getById(member.getSubOrgId());

            ClusterFileResolver fileResolver = coreFileService
                    .resolveClusterFileInfo(authSession.getOrgId(), member.getSubOrgId());
            Path reportPath = fileResolver.getMemberReportVoterXlsxPath(member.getMemberId());

            OutputStream wbOutputStream = Files.newOutputStream(reportPath);
            Workbook workbook = new Workbook(wbOutputStream, "EzAppService", "1.0");
            workbook.properties()
                    .setTitle("Report Auto Generated")
                    .setCategory("Data Export")
                    .setDescription("EzAppService Auto Report");

            // start valid sheet
            Worksheet validWs = workbook.newWorksheet("Data Valid");
            validWs.value(0, 0, "Nama");
            validWs.value(0, 1, "NIK");
            validWs.value(0, 2, "No HP");
            validWs.value(0, 3, "Kecamatan");
            validWs.value(0, 4, "Pekon");
            validWs.value(0, 5, "No TPS");
            validWs.value(0, 6, "Pendidikan");
            validWs.value(0, 7, "Pekerjaan");
            validWs.value(0, 8, "Etnis");

            List<BizVoter> bizVoters = bizVoterDAO
                    .getVoterByReferrer(authSession.getOrgId(), member.getMemberId());
            int row = 1;
            for (BizVoter bizVoter : bizVoters) {
                validWs.value(row, 0, bizVoter.getName());
                validWs.value(row, 1, bizVoter.getIdCardNumber());
                validWs.value(row, 2, bizVoter.getPhone());
                validWs.value(row, 3, bizVoter.getDistrictName());
                validWs.value(row, 4, bizVoter.getVillageName());
                validWs.value(row, 5, bizVoter.getPollStationId());
                validWs.value(row, 6, bizVoter.getEducation());
                validWs.value(row, 7, bizVoter.getOccupation());
                validWs.value(row, 8, bizVoter.getEthnic());
                row++;
            }
            validWs.finish();

            // start invalid sheet
            Worksheet inValidWs = workbook.newWorksheet("Data Invalid");
            inValidWs.value(0, 0, "Nama");
            inValidWs.value(0, 1, "NIK");
            inValidWs.value(0, 2, "No HP");
            inValidWs.value(0, 3, "Kecamatan");
            inValidWs.value(0, 4, "Pekon");
            inValidWs.value(0, 5, "No TPS");
            inValidWs.value(0, 6, "Pendidikan");
            inValidWs.value(0, 7, "Pekerjaan");
            inValidWs.value(0, 8, "Etnis");

            List<BizVoterInvalid> bizVoterInvalids = bizVoterInvalidDAO
                    .getByReferrerId(authSession.getOrgId(), member.getMemberId());
            int invRow = 1;
            for (BizVoterInvalid invalidVoter : bizVoterInvalids) {
                inValidWs.value(invRow, 0, invalidVoter.getName());
                inValidWs.value(invRow, 1, invalidVoter.getIdCardNumber());
                inValidWs.value(invRow, 2, invalidVoter.getPhone());
                inValidWs.value(invRow, 3, invalidVoter.getDistrictName());
                inValidWs.value(invRow, 4, invalidVoter.getVillageName());
                inValidWs.value(invRow, 5, invalidVoter.getPollStationId());
                inValidWs.value(invRow, 6, invalidVoter.getEducation());
                inValidWs.value(invRow, 7, invalidVoter.getOccupation());
                inValidWs.value(invRow, 8, invalidVoter.getEthnic());
                invRow++;
            }
            inValidWs.finish();
            workbook.finish();
            workbook.close();
            wbOutputStream.close();

            response.setContentType("application/vnd.openxmlformats-officedocument");
            String fileName = (subOrganization.getName() +"_"+ member.getName())
                    .toUpperCase()
                    .replace(" ", "_");
            response.setHeader(
                    HttpHeaders.CONTENT_DISPOSITION,
                    ContentDisposition.attachment()
                            .filename(fileName +".xlsx", StandardCharsets.UTF_8)
                            .build()
                            .toString()
            );
            Files.copy(reportPath, response.getOutputStream());

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

    @GetMapping(value = "/biz/data/survey/{secretToken}")
    private void getSurveyResponse(
            @PathVariable("secretToken") String secretToken,
            HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");

        try {
            AssertUtil.notBlank(secretToken, EzErrorCode.ILLEGAL_PARAM);
            AssertUtil.isTrue(secretToken.length() == 64, EzErrorCode.ILLEGAL_PARAM);

            String sessionId = StringUtil.leftSubstring(secretToken, 32);
            String tableDataId = secretToken.substring(32);
            AuthSession session = authAdminService.authorizeWebPublicSession(sessionId);

            BizSurveyTable table = bizSurveyTableDAO.getByTableId(tableDataId);
            AssertUtil.notNull(table, EzErrorCode.ILLEGAL_PARAM);

            List<String> dataKeys = Arrays.asList(table.getDataMap().split(","));
            List<List<String>> parsedData = new ArrayList<>();

            List<AppCommonDataSurvey> dataSurveys = appCommonDataSurveyDAO
                    .getData(session.getOrgId(), table.getSurveyId());
            for (AppCommonDataSurvey dataSurvey : dataSurveys) {
                parsedData.add(getRowData(dataSurvey, dataKeys));
            }

            String layout = bizPageLayoutDAO.getContent("BIZ_DATA_SURVEY");
            String htmlContent = layout
                    .replace("_TABLE_TITLE_", table.getTitle())
                    .replace("_TABLE_COLUMN_", table.getColumn())
                    .replace("_TABLE_ROW_DATA_", bizObjectMapperService.toJson(parsedData));

            response.setStatus(HttpStatus.OK.value());
            response.getWriter().write(htmlContent);
            response.getWriter().flush();
        } catch (Exception e) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
    }

    @GetMapping(value = "/biz/data/downloadSurvey/{secretToken}")
    private void downloadSurveyData(
            @PathVariable("secretToken") String secretToken,
            HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");

        try {
            AssertUtil.notBlank(secretToken, EzErrorCode.ILLEGAL_PARAM);
            AssertUtil.isTrue(secretToken.length() == 64, EzErrorCode.ILLEGAL_PARAM);

            String sessionId = StringUtil.leftSubstring(secretToken, 32);
            String tableDataId = secretToken.substring(32);
            AuthSession session = authAdminService.authorizeWebPublicSession(sessionId);

            BizSurveyTable table = bizSurveyTableDAO.getByTableId(tableDataId);
            AssertUtil.notNull(table, EzErrorCode.ILLEGAL_PARAM);

            PublicFileResolver publicFileResolver = coreFileService
                    .resolvePublicFileInfo(session.getOrgId());
            Path reportPath = publicFileResolver.getOtherPath("SURVEY_RESPONSE_DATA_"+ table.getSurveyId() + ".xlsx");

            OutputStream wbOutputStream = Files.newOutputStream(reportPath);
            Workbook workbook = new Workbook(wbOutputStream, "EzAppService", "1.0");
            workbook.properties()
                    .setTitle("Data Respon Survey")
                    .setCategory("Data Export")
                    .setDescription("EzAppService Auto Report");

            // start valid sheet
            Worksheet dataWs = workbook.newWorksheet("Data Response");
            dataWs.value(0, 0, "Surveyor");
            dataWs.value(0, 1, "Nama Responder");
            dataWs.value(0, 2, "Jenis Kelamin");
            dataWs.value(0, 3, "Usia");
            dataWs.value(0, 4, "Pendidikan");
            dataWs.value(0, 5, "Agama");
            dataWs.value(0, 6, "Pekerjaan");
            dataWs.value(0, 7, "Suku");
            dataWs.value(0, 8, "Alamat");
            dataWs.value(0, 9, "Paslon Pilihan");
            dataWs.value(0, 10, "Akankan Memilih di TPS");
            dataWs.value(0, 11, "Alasan Memilih");
            dataWs.value(0, 12, "Sumber Informasi Tentang Paslon");
            dataWs.value(0, 13, "Pengaruh Politik Uang");

            List<AppCommonDataSurvey> dataSurveys = appCommonDataSurveyDAO
                    .getData(session.getOrgId(), table.getSurveyId());

            int row = 1;
            for (AppCommonDataSurvey dataSurvey : dataSurveys) {
                dataWs.value(row, 0, dataSurvey.submitterName);
                dataWs.value(row, 1, dataSurvey.r001);
                dataWs.value(row, 2, dataSurvey.r002);
                dataWs.value(row, 3, dataSurvey.r003);
                dataWs.value(row, 4, dataSurvey.r004);
                dataWs.value(row, 5, dataSurvey.r005);
                dataWs.value(row, 6, dataSurvey.r006);
                dataWs.value(row, 7, dataSurvey.r007);
                dataWs.value(row, 8, dataSurvey.r008);
                dataWs.value(row, 9, dataSurvey.q001a);
                dataWs.value(row, 10, dataSurvey.q002a);
                dataWs.value(row, 11, dataSurvey.q003a);
                dataWs.value(row, 12, dataSurvey.q004a);
                dataWs.value(row, 13, dataSurvey.q005a);
                row++;
            }
            dataWs.finish();

            workbook.finish();
            workbook.close();
            wbOutputStream.close();

            response.setContentType("application/vnd.openxmlformats-officedocument");
            String fileName = "DATA_RESPON_SURVEY"
                    .toUpperCase()
                    .replace(" ", "_");
            response.setHeader(
                    HttpHeaders.CONTENT_DISPOSITION,
                    ContentDisposition.attachment()
                            .filename(fileName +".xlsx", StandardCharsets.UTF_8)
                            .build()
                            .toString()
            );
            Files.copy(reportPath, response.getOutputStream());

        } catch (Exception e) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
    }

    private List<String> getRowData(AppCommonDataSurvey dataSurvey, List<String> dataKeys) {
        List<String> rowData = new ArrayList<>();
        try {
            for (String dataKey : dataKeys) {
                for (Field field : dataSurvey.getClass().getDeclaredFields()) {
                    if (field.getName().equals(dataKey)) {
                        field.setAccessible(true);
                        rowData.add((String) field.get(dataSurvey));
                    }
                }
            }
        } catch (Exception ignored) {}

        return rowData;
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
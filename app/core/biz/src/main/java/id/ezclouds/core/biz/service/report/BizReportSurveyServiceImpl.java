/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.biz.service.report;

import id.ezclouds.common.facade.auth.AuthAdminService;
import id.ezclouds.common.facade.biz.report.BizReportSurveyService;
import id.ezclouds.common.facade.dal.biz.AppCommonDataSurveyDAO;
import id.ezclouds.common.facade.dal.biz.BizSurveyTableDAO;
import id.ezclouds.common.model.auth.AuthSession;
import id.ezclouds.common.model.biz.report.BizSurveyReport;
import id.ezclouds.common.model.biz.survey.BizSurveyTable;
import id.ezclouds.common.model.constant.BizSurveyGroupQuery;
import id.ezclouds.common.model.constant.SurveyGroupQuery;
import id.ezclouds.common.model.query.BizGroupQueryCount;
import id.ezclouds.common.model.query.BizSurveyGroupQueryParam;
import id.ezclouds.common.model.result.BizResult;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportSurveyServiceImpl.java, v 0.1 2024‐11‐23 8:37 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizReportSurveyServiceImpl implements BizReportSurveyService {

    @Autowired
    private AuthAdminService authAdminService;

    @Autowired
    private BizSurveyTableDAO bizSurveyTableDAO;

    @Autowired
    private AppCommonDataSurveyDAO appCommonDataSurveyDAO;

    @Override
    public BizResult getSurveyReport(String secretToken) {
        BizResult result = new BizResult();
        result.setSuccess(true);
        final List<BizSurveyReport> surveyReports = new ArrayList<>();
        result.setObject(surveyReports);

        try {
            AssertUtil.notBlank(secretToken, EzErrorCode.ILLEGAL_PARAM);
            String sessionId = StringUtil.leftSubstring(secretToken, 32);
            String tableDataId = secretToken.substring(32);
            AuthSession session = authAdminService.authorizeWebPublicSession(sessionId);

            BizSurveyTable table = bizSurveyTableDAO.getByTableId(tableDataId);
            AssertUtil.notNull(table, EzErrorCode.ILLEGAL_PARAM);

            List<BizSurveyGroupQuery> surveyGroupQueryList = new ArrayList<>();
            surveyGroupQueryList.add(new BizSurveyGroupQuery("Kecenderungan Pilihan", SurveyGroupQuery.BY_RESPONSE_01));
            surveyGroupQueryList.add(new BizSurveyGroupQuery("Keikutsertaan Memilih", SurveyGroupQuery.BY_RESPONSE_02));
            surveyGroupQueryList.add(new BizSurveyGroupQuery("Alasan Memilih", SurveyGroupQuery.BY_RESPONSE_03));
            surveyGroupQueryList.add(new BizSurveyGroupQuery("Sumber/Media Informasi", SurveyGroupQuery.BY_RESPONSE_04));
            surveyGroupQueryList.add(new BizSurveyGroupQuery("Pengaruh Politik Uang", SurveyGroupQuery.BY_RESPONSE_05));

            for (BizSurveyGroupQuery bizSurveyGroupQuery : surveyGroupQueryList) {
                BizSurveyReport bizSurveyReport = new BizSurveyReport();
                bizSurveyReport.setTitle(bizSurveyGroupQuery.getTitle());

                BizSurveyGroupQueryParam param = new BizSurveyGroupQueryParam();
                param.setOrgId(session.getOrgId());
                param.setSurveyId(table.getSurveyId());
                param.setGroupQuery(bizSurveyGroupQuery.getGroupQuery());
                List<BizGroupQueryCount> groupQueryCounts = appCommonDataSurveyDAO
                        .getGroupQueryCount(param);

                for (BizGroupQueryCount groupQueryCount : groupQueryCounts) {
                    List<String> groupDataList = new ArrayList<>();
                    groupDataList.add(groupQueryCount.getGroupId());
                    groupDataList.add(String.valueOf(groupQueryCount.getGroupCount()));
                    bizSurveyReport.getData().add(groupDataList);
                }
                surveyReports.add(bizSurveyReport);
            }
        } catch (Exception ignored) {
            return result;
        }

        return result;
    }

    @Override
    public BizResult getSurveyorPerformance(String secretToken) {
        BizResult result = new BizResult();
        result.setSuccess(true);
        List<List<String>> data = new ArrayList<>();
        result.setObject(data);

        try {
            AssertUtil.notBlank(secretToken, EzErrorCode.ILLEGAL_PARAM);
            String sessionId = StringUtil.leftSubstring(secretToken, 32);
            String tableDataId = secretToken.substring(32);
            AuthSession session = authAdminService.authorizeWebPublicSession(sessionId);

            BizSurveyTable table = bizSurveyTableDAO.getByTableId(tableDataId);
            AssertUtil.notNull(table, EzErrorCode.ILLEGAL_PARAM);

            BizSurveyGroupQueryParam param = new BizSurveyGroupQueryParam();
            param.setOrgId(session.getOrgId());
            param.setSurveyId(table.getSurveyId());
            param.setGroupQuery(SurveyGroupQuery.BY_SUBMITTER);

            List<BizGroupQueryCount> groupQueryCounts = appCommonDataSurveyDAO
                    .getGroupQueryCount(param);
            for (BizGroupQueryCount groupQueryCount : groupQueryCounts) {
                List<String> rowData = new ArrayList<>();
                rowData.add(groupQueryCount.getGroupId());
                rowData.add(groupQueryCount.getGroupLabel());
                rowData.add(String.valueOf(groupQueryCount.getGroupCount()));
                data.add(rowData);
            }

        } catch (Exception ignored) {
            return result;
        }

        return result;
    }
}
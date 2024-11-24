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
import id.ezclouds.common.model.biz.survey.BizSurveyTable;
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
    public BizResult getSurveyRecap(String secretToken) {
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
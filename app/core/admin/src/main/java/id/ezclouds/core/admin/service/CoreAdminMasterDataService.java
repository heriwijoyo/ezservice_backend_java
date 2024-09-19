/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.admin.service;

import id.ezclouds.common.facade.auth.AuthAdminService;
import id.ezclouds.common.facade.biz.admin.BizAdminMasterDataService;
import id.ezclouds.common.facade.biz.data.BizMasterDataService;
import id.ezclouds.common.facade.biz.report.BizReportOverallService;
import id.ezclouds.common.facade.broker.EzEventPublisherService;
import id.ezclouds.common.facade.template.BizServiceTemplate;
import id.ezclouds.common.model.auth.AuthAdminSession;
import id.ezclouds.common.model.auth.AuthRole;
import id.ezclouds.common.model.broker.event.EzCommonEvent;
import id.ezclouds.common.model.broker.event.EzCommonEventData;
import id.ezclouds.common.model.message.CommonMessageConstant;
import id.ezclouds.common.model.report.BizReportOverall;
import id.ezclouds.common.model.report.BizReportOverallKey;
import id.ezclouds.common.model.request.WebBizPageRequest;
import id.ezclouds.common.model.request.admin.WebBizDetailRequest;
import id.ezclouds.common.model.request.admin.WebBizUpdateRequest;
import id.ezclouds.common.model.result.BizResult;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.BizErrorMessageHelper;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreAdminMasterDataService.java, v 0.1 2024‐09‐18 12:56 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreAdminMasterDataService implements BizAdminMasterDataService {

    @Autowired
    private AuthAdminService authAdminService;

    @Autowired
    private BizReportOverallService bizReportOverallService;

    @Autowired
    private BizMasterDataService bizMasterDataService;

    @Autowired
    private EzEventPublisherService ezEventPublisherService;

    @Override
    public BizResult getReportOverall(WebBizPageRequest request) {
        final BizResult result = new BizResult();
        BizServiceTemplate.execute(null, result, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getSessionId(), EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {
                AuthAdminSession session = authAdminService
                        .authenticateAdminSession(request.getSessionId());
                authAdminService.authorizeSessionForRole(session, AuthRole.ADMIN_ORG);

                List<List<String>> data = new ArrayList<>();

                List<String> excludeKeys = new ArrayList<>();
                excludeKeys.add(BizReportOverallKey.TOTAL_SUB_ORGANIZATION.getCode());
                excludeKeys.add(BizReportOverallKey.TOTAL_MEMBER_UNION.getCode());
                excludeKeys.add(BizReportOverallKey.TOTAL_TPS.getCode());
                excludeKeys.add(BizReportOverallKey.MEMBER_TODAY.getCode());
                excludeKeys.add(BizReportOverallKey.MEMBER_YESTERDAY.getCode());
                excludeKeys.add(BizReportOverallKey.REAL_COUNT_VOTER_ALL_COUNT.getCode());
                excludeKeys.add(BizReportOverallKey.REAL_COUNT_VOTER_VERIFIED_COUNT.getCode());
                excludeKeys.add(BizReportOverallKey.VOTER_BASE_CLUSTER_COUNT.getCode());
                excludeKeys.add(BizReportOverallKey.VOTER_BASE_MEMBER_COUNT.getCode());
                excludeKeys.add(BizReportOverallKey.VOTER_BASE_VOTER_COUNT.getCode());
                excludeKeys.add(BizReportOverallKey.VOTER_BASE_VOTE_STATION_COUNT.getCode());
                List<BizReportOverall> overalls = bizReportOverallService
                        .getReportOverall(session.getOrgId())
                        .stream()
                        .filter(report -> !excludeKeys.contains(report.getKeyId()))
                        .collect(Collectors.toList());

                for (BizReportOverall reportOverall : overalls) {
                    BizReportOverallKey overallKey = BizReportOverallKey
                            .getByCode(reportOverall.getKeyId());

                    if (overallKey != null) {
                        List<String> row = new ArrayList<>();
                        row.add(reportOverall.getKeyId());
                        row.add(overallKey.getDescription());
                        row.add(String.valueOf(reportOverall.getCount()));
                        data.add(row);
                    }
                }
                result.setObject(data);
                result.setSuccess(true);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return BizErrorMessageHelper.getBizErrorMessage(ezErrorCode);
            }
        });
        return result;
    }

    @Override
    public BizResult reportOverallUpdate(WebBizUpdateRequest<BizReportOverall> request) {
        final BizResult result = new BizResult();
        BizServiceTemplate.execute(null, result, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getSessionId(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notNull(request.getObject(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getObject().getKeyId(), EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {
                AuthAdminSession session = authAdminService
                        .authenticateAdminSession(request.getSessionId());
                authAdminService.authorizeSessionForRole(session, AuthRole.ADMIN_ORG);

                BizReportOverall report = request.getObject();
                bizReportOverallService.updateReportOverall(session.getOrgId(), report.getKeyId(), report.getCount());

                ezEventPublisherService.publish(new EzCommonEventData(session.getOrgId(), EzCommonEvent.REPORT_OVERALL_CHANGE, null));

                result.setSuccess(true);
                result.setObject(CommonMessageConstant.BIZ_OPERATION_SUCCESS);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return BizErrorMessageHelper.getBizErrorMessage(ezErrorCode);
            }
        });
        return result;
    }

    @Override
    public BizResult getMasterDataAreaVillage(WebBizDetailRequest<String> request) {
        final BizResult result = new BizResult();
        BizServiceTemplate.execute(null, result, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getSessionId(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getObject(), EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {
                AuthAdminSession session = authAdminService
                        .authenticateAdminSession(request.getSessionId());
                authAdminService.authorizeSessionForRole(session, AuthRole.ADMIN_ORG);

                result.setObject(bizMasterDataService.getVillageMasterData(session.getOrgId(), request.getObject()));
                result.setSuccess(true);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return BizErrorMessageHelper.getBizErrorMessage(ezErrorCode);
            }
        });
        return result;
    }
}
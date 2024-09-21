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
import id.ezclouds.common.model.biz.data.BizMasterDataUpdate;
import id.ezclouds.common.model.biz.data.OverallMasterData;
import id.ezclouds.common.model.biz.data.VillageMasterData;
import id.ezclouds.common.model.message.CommonMessageConstant;
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

import java.util.Arrays;
import java.util.List;

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
    public BizResult getMasterDataOverall(WebBizPageRequest request) {
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
                result.setObject(bizMasterDataService.getOverallMasterData(session.getOrgId()));
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
    public BizResult updateMasterDataOverall(WebBizUpdateRequest<BizMasterDataUpdate> request) {
        final BizResult result = new BizResult();
        BizServiceTemplate.execute(null, result, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getSessionId(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notNull(request.getObject(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getObject().getBizMasterId(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.isTrue(request.getObject().isValidNumber(), EzErrorCode.DATA_INVALID);
            }

            @Override
            public void onBizProcess() throws Exception {
                AuthAdminSession session = authAdminService
                        .authenticateAdminSession(request.getSessionId());
                authAdminService.authorizeSessionForRole(session, AuthRole.ADMIN_ORG);

                OverallMasterData masterData = new OverallMasterData();
                masterData.setBizMasterId(request.getObject().getBizMasterId());
                masterData.setValueCount(request.getObject().getIntValue());
                bizMasterDataService.updateOverallMasterData(masterData);

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

    @Override
    public BizResult updateMasterDataAreaVillage(String sessionId, String bizMasterId, String values) {
        final BizResult result = new BizResult();
        BizServiceTemplate.execute(null, result, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notBlank(sessionId, EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(bizMasterId, EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(values, EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {
                List<String> valueList = Arrays.asList(values.split(","));
                AssertUtil.isTrue(valueList.size() >= 4, EzErrorCode.ILLEGAL_PARAM);
                for (String eachValue : valueList) {
                    AssertUtil.isNumber(eachValue, EzErrorCode.INVALID_NUMBER_FORMAT);
                }

                AuthAdminSession session = authAdminService
                        .authenticateAdminSession(sessionId);
                authAdminService.authorizeSessionForRole(session, AuthRole.ADMIN_ORG);

                VillageMasterData masterData = new VillageMasterData();
                masterData.setBizMasterId(bizMasterId);
                masterData.setVoterTotal(Integer.parseInt(valueList.get(0)));
                masterData.setVoterMale(Integer.parseInt(valueList.get(1)));
                masterData.setVoterFemale(Integer.parseInt(valueList.get(2)));
                masterData.setPollStationTotal(Integer.parseInt(valueList.get(3)));

                bizMasterDataService.updateVillageMasterData(masterData);

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
}
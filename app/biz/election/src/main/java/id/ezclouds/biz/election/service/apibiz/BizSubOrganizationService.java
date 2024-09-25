/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.apibiz;

import id.ezclouds.biz.election.service.app.request.BizSubOrgCreateRequest;
import id.ezclouds.biz.election.service.request.BizPageRequest;
import id.ezclouds.biz.election.subbiz.arahindonesia.model.BizSubOrganization;
import id.ezclouds.biz.election.subbiz.arahindonesia.service.AppSubOrganizationService;
import id.ezclouds.common.model.result.BizPageInfo;
import id.ezclouds.common.model.result.BizResult;
import id.ezclouds.common.facade.template.BizServiceTemplate;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.core.auth.result.CoreAuthMemberSessionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizSubOrganizationService.java, v 0.1 2024‐04‐25 9:48 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizSubOrganizationService extends BizBaseService {

    @Autowired
    private AppSubOrganizationService appSubOrganizationService;

    public BizResult create(BizSubOrgCreateRequest request) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(request, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getName(), EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {
                CoreAuthMemberSessionInfo session = authAppMemberSession();
                authorizeAdminMember(session.getMemberRoles());
                appSubOrganizationService.create(request.getName(), request.getAddress(), getOrgId(), getOrgCode());

                bizResult.setSuccess(true);
                bizResult.setObject("SubOrganization Created");
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });
        return bizResult;
    }

    public BizResult getOrgSubOrganizations() {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {}

            @Override
            public void onBizProcess() throws Exception {
                authAppMemberSession();
                List<BizSubOrganization> subOrganizations = appSubOrganizationService.getSubOrganizationByOrgId(getOrgId());
                bizResult.setObject(subOrganizations);
                bizResult.setSuccess(true);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });
        return bizResult;
    }

    public BizResult getSubBizOrganizations(BizPageRequest request) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(request, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
                adjustBizPageRequest(request);
            }

            @Override
            public void onBizProcess() throws Exception {
                CoreAuthMemberSessionInfo session = authAppMemberSession();
                authorizeAdminMember(session.getMemberRoles());

                request.setSortBy("createdTime");
                request.setSort("DESC");
                BizPageInfo bizPageInfo = appSubOrganizationService.pageQuery(request);
                bizResult.setBizPageInfo(bizPageInfo);
                bizResult.setSuccess(true);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });
        return bizResult;
    }
}
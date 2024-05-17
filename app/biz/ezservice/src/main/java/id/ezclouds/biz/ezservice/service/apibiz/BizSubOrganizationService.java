/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.apibiz;

import id.ezclouds.biz.ezservice.service.app.request.BizSubOrgCreateRequest;
import id.ezclouds.biz.ezservice.service.request.BizPageRequest;
import id.ezclouds.core.shared.result.BizPageInfo;
import id.ezclouds.biz.ezservice.service.result.BizResult;
import id.ezclouds.biz.ezservice.service.template.BizServiceTemplate;
import id.ezclouds.biz.ezservice.subbiz.arahindonesia.service.AppSubOrganizationService;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.core.auth.result.CoreAuthMemberSessionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                appSubOrganizationService.create(request.getName(), getOrgId(), getOrgCode());

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

    public BizResult getSubBizOrganizations(BizPageRequest request) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(request, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
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
/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.biz.service.admin;

import id.ezclouds.common.facade.auth.AuthAdminService;
import id.ezclouds.common.facade.biz.admin.BizAdminMemberUpdateService;
import id.ezclouds.common.facade.member.MemberBackOfficeService;
import id.ezclouds.common.facade.organization.SubOrganizationService;
import id.ezclouds.common.facade.template.BizServiceTemplate;
import id.ezclouds.common.model.auth.AuthAdminSession;
import id.ezclouds.common.model.auth.AuthRole;
import id.ezclouds.common.model.constant.MapKey;
import id.ezclouds.common.model.member.MemberBackOffice;
import id.ezclouds.common.model.message.CommonMessageConstant;
import id.ezclouds.common.model.organization.SubOrganization;
import id.ezclouds.common.model.request.admin.WebBizUpdateRequest;
import id.ezclouds.common.model.result.BizResult;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.BizErrorMessageHelper;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreAdminMemberUpdateService.java, v 0.1 2024‐08‐29 11:10 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreAdminMemberUpdateService implements BizAdminMemberUpdateService {

    @Autowired
    private AuthAdminService authAdminService;

    @Autowired
    private MemberBackOfficeService memberBackOfficeService;

    @Autowired
    private SubOrganizationService subOrganizationService;

    @Override
    public BizResult updateMemberBackOffice(WebBizUpdateRequest<Map<String, String>> request) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(request, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getSessionId(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notNull(request.getObject(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getObject().get(MapKey.MEMBER_ID), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getObject().get(MapKey.SUB_ORGANIZATION_ID), EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {
                AuthAdminSession session = authAdminService
                        .authenticateAdminSession(request.getSessionId());
                authAdminService.authorizeSessionForRole(session, AuthRole.ADMIN_ORG);

                String memberId = request.getObject().get(MapKey.MEMBER_ID);
                String subOrganizationId = request.getObject().get(MapKey.SUB_ORGANIZATION_ID);

                // validate member data
                MemberBackOffice memberBackOffice = memberBackOfficeService
                        .getMemberDetail(memberId);
                AssertUtil.notNull(memberBackOffice, EzErrorCode.DATA_NOT_FOUND);
                AssertUtil.equals(session.getOrgId(), memberBackOffice.getOrgId(), EzErrorCode.ACTION_NOT_ALLOWED);

                // validate sub organization
                SubOrganization subOrganization = subOrganizationService
                        .getById(subOrganizationId);
                AssertUtil.notNull(subOrganization, EzErrorCode.DATA_NOT_FOUND);
                AssertUtil.equals(subOrganization.getSubOrgId(), subOrganizationId, EzErrorCode.ACTION_NOT_ALLOWED);

                memberBackOfficeService.memberUpdateSubOrganization(memberBackOffice, subOrganizationId);

                bizResult.setSuccess(true);
                bizResult.setObject(CommonMessageConstant.BIZ_OPERATION_SUCCESS);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return BizErrorMessageHelper.getBizErrorMessage(ezErrorCode);
            }
        });
        return bizResult;
    }
}
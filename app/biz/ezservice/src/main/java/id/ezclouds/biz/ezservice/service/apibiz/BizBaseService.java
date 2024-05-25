/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.apibiz;

import id.ezclouds.biz.ezservice.config.BizPublicUrlResolver;
import id.ezclouds.biz.ezservice.config.BizPublicUrlResolverImpl;
import id.ezclouds.biz.ezservice.constant.AppConstant;
import id.ezclouds.biz.ezservice.enums.BizMemberRole;
import id.ezclouds.biz.ezservice.service.request.BizPageRequest;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.core.auth.result.CoreAuthMemberSessionInfo;
import id.ezclouds.core.auth.service.CoreAuthService;
import id.ezclouds.core.shared.context.EzAppContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Arrays;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizBaseService.java, v 0.1 2024‐02‐04 1:28 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizBaseService {

    @Autowired
    protected CoreAuthService coreAuthService;

    @Value("${ezserviceapp.url.public.root}")
    protected String appRootPublicUrl;

    public String getOrgId() {
        return EzAppContextHolder.getContext().getOrgId();
    }

    public String getOrgCode() {
        return EzAppContextHolder.getContext().getOrgCode();
    }

    public String getAppId() {
        return EzAppContextHolder.getContext().getAppId();
    }

    public BizPublicUrlResolver getPublicOrgUrlResolver(String orgCode) {
        return new BizPublicUrlResolverImpl(appRootPublicUrl, orgCode);
    }

    public BizPublicUrlResolver getPublicOrgUrlResolver(String orgCode, String memberId) {
        return new BizPublicUrlResolverImpl(appRootPublicUrl, orgCode, memberId);
    }

    protected CoreAuthMemberSessionInfo authAppMemberSession() throws Exception {
        String sessionId = EzAppContextHolder.getContext().getMemberSessionId();
        AssertUtil.notBlank(sessionId, EzErrorCode.SESSION_INVALID);
        return coreAuthService.authMemberSession(sessionId);
    }

    protected String getBizErrorMessage(EzErrorCode ezErrorCode) {
        switch (ezErrorCode) {
            // Member Login
            case MEMBER_CLIENT_NOT_FOUND:
                return AppConstant.MEMBER_LOGIN_MESSAGE_NOT_FOUND;
            case MEMBER_CLIENT_FROZEN:
            case MEMBER_CLIENT_NOT_ACTIVE:
                //TODO: revisit the usage of abnormal status
            case MEMBER_CLIENT_ABNORMAL:
                return AppConstant.MEMBER_LOGIN_MESSAGE_SUSPEND;
            case MEMBER_LOGIN_FAILED:
                return AppConstant.MEMBER_LOGIN_MESSAGE_FAILED;

            case MEMBER_UNAUTHORIZED:
                return AppConstant.BizMessage.MEMBER_UNAUTHORIZED;

            //Session Error
            case SESSION_INVALID:
                return AppConstant.BizMessage.SESSION_INVALID;
            case SESSION_UNAVAILABLE:
                return AppConstant.BizMessage.SESSION_UNAVAILABLE;
            case SESSION_EXPIRED:
                return AppConstant.BizMessage.SESSION_EXPIRED;
            case SESSION_VERIFY_FAILED:
                return AppConstant.BizMessage.SESSION_VERIFY_FAILED;

            // Multipart Common Upload
            case MULTIPARTFILE_EMPTY:
                return AppConstant.BizMessage.MULTIPARTFILE_EMPTY;
            case MULTIPARTFILE_TYPE_UNSUPPORTED:
                return AppConstant.BizMessage.MULTIPARTFILE_TYPE_UNSUPPORTED;

            // General Error
            case ILLEGAL_ACTION:
                return AppConstant.BizMessage.ILLEGAL_ACTION;
            case ILLEGAL_PARAM:
                return "Param Illegal";
            case DATA_NOT_FOUND:
                return "Data not found";
            default:
                return AppConstant.MESSAGE_SYSTEM_ABNORMAL;
        }
    }

    protected void authorizeAdminMember(String memberRoles) throws EzErrorException {
        AssertUtil.notBlank(memberRoles, EzErrorCode.MEMBER_UNAUTHORIZED);
        List<String> roles = Arrays.asList(memberRoles.split(","));
        AssertUtil.isTrue(roles.contains(BizMemberRole.ADMIN_ORG.getCode()), EzErrorCode.MEMBER_UNAUTHORIZED);
    }

    protected void adjustBizPageRequest(BizPageRequest request) {
        if (request.getPageNumber() < 1) {
            request.setPageNumber(1);
        }
        if (request.getPageSize() < 1) {
            request.setPageSize(20);
        }
    }
}
/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.core.converter;

import id.ezclouds.biz.ezservice.enums.BizUploadScene;
import id.ezclouds.biz.ezservice.model.member.BizGender;
import id.ezclouds.biz.ezservice.service.app.request.BizSubOrgCreateRequest;
import id.ezclouds.biz.ezservice.service.request.*;
import id.ezclouds.core.bifrost.app.api.request.*;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizRequestConverter.java, v 0.1 2024‐01‐01 11:42 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizRequestConverter<T extends BizRequest> {

    private Handler<T> handler;
    public BizRequestConverter(Handler<T> handler) {
        this.handler = handler;
    }

    public T convert(ApiRequest input) {
        return handler.convert(input);
    }

    public static Handler<BizMemberLoginRequest> MEMBER_LOGIN = apiRequest -> {
        if (apiRequest instanceof MemberLoginRequest) {
            MemberLoginRequest request = (MemberLoginRequest) apiRequest;
            BizMemberLoginRequest memberLogin = new BizMemberLoginRequest();
            memberLogin.setLoginType(request.getLoginType());
            memberLogin.setLoginId(request.getLoginId());
            memberLogin.setLoginPassword(request.getLoginPassword());
            return memberLogin;
        }
        return null;
    };

    public static Handler<BizMemberRegisterRequest> MEMBER_REGISTER = apiRequest -> {
        if (apiRequest instanceof MemberRegisterRequest) {
            MemberRegisterRequest request = (MemberRegisterRequest) apiRequest;
            BizMemberRegisterRequest bizRequest = new BizMemberRegisterRequest();
            bizRequest.setSubOrgId(request.getSubOrgId());
            bizRequest.setReferrerId(request.getReferrerId());
            bizRequest.setRoles(request.getRoles());
            bizRequest.setName(request.getName());
            bizRequest.setNickname(request.getNickname());
            bizRequest.setDateOfBirth(request.getDateOfBirth());
            bizRequest.setPhone(request.getPhone());
            bizRequest.setEmail(request.getEmail());
            bizRequest.setAvatarUrl(request.getAvatarUrl());
            bizRequest.setAddress(request.getAddress());
            bizRequest.setBizGender(BizGender.getByCode(request.getGender()));
            bizRequest.setProvinceId(request.getProvinceId());
            bizRequest.setProvinceName(request.getProvinceName());
            bizRequest.setRegencyId(request.getRegencyId());
            bizRequest.setRegencyName(request.getRegencyName());
            bizRequest.setDistrictId(request.getDistrictId());
            bizRequest.setDistrictName(request.getDistrictName());
            bizRequest.setVillageId(request.getVillageId());
            bizRequest.setVillageName(request.getVillageName());
            bizRequest.setRukunWarga(request.getRukunWarga());
            bizRequest.setRukunTetangga(request.getRukunTetangga());
            bizRequest.setTpsNumber(request.getTpsNumber());
            return bizRequest;
        }
        return null;
    };

    public static Handler<BizMemberUpdatePasswordRequest> UPDATE_PASSWORD = apiRequest -> {
        if (apiRequest instanceof MemberUpdatePasswordRequest) {
            MemberUpdatePasswordRequest request = (MemberUpdatePasswordRequest) apiRequest;
            BizMemberUpdatePasswordRequest bizRequest = new BizMemberUpdatePasswordRequest();
            bizRequest.getExtendInfo().putAll(apiRequest.getExtendInfo());
            bizRequest.setMode(request.getMode());
            bizRequest.setNewPassword(request.getNewPassword());
            return bizRequest;
        }
        return null;
    };

    public static Handler<BizMemberResetPasswordRequest> RESET_PASSWORD = apiRequest -> {
        if (apiRequest instanceof MemberResetPasswordRequest) {
            MemberResetPasswordRequest request = (MemberResetPasswordRequest) apiRequest;
            BizMemberResetPasswordRequest bizRequest = new BizMemberResetPasswordRequest();
            bizRequest.setLoginType(request.getLoginType());
            bizRequest.setLoginId(request.getLoginId());
            return bizRequest;
        }
        return null;
    };

    public static Handler<BizVerifyCommonSessionRequest> VERIFY_COMMON_SESSION = apiRequest -> {
        if (apiRequest instanceof VerifyCommonSessionRequest) {
            VerifyCommonSessionRequest request = (VerifyCommonSessionRequest) apiRequest;
            BizVerifyCommonSessionRequest bizRequest = new BizVerifyCommonSessionRequest();
            bizRequest.setSessionId(request.getSessionId());
            bizRequest.setScene(request.getScene());
            bizRequest.setVerifyStrategy(request.getVerifyStrategy());
            bizRequest.setVerifyCode(request.getVerifyCode());
            return bizRequest;
        }
        return null;
    };

    public static Handler<BizSurveySubmitRequest> SURVEY_SUBMIT =  apiRequest -> {
        if (apiRequest instanceof SurveySubmitRequest) {
            SurveySubmitRequest request = (SurveySubmitRequest) apiRequest;
            BizSurveySubmitRequest bizRequest = new BizSurveySubmitRequest();
            bizRequest.setRequestId(request.getExtendInfo().get("REQUEST_ID"));
            bizRequest.setSurveyId(request.getSurveyId());
            bizRequest.setQuestionVersion(request.getQuestionnaireVersion());
            bizRequest.setResponderDataEncoded(request.getResponderDataEncoded());
            bizRequest.setResponseDataEncoded(request.getResponseDataEncoded());
            return bizRequest;
        }
        return null;
    };

    public static Handler<BizMemberUploadRequest> BIZ_COMMON_UPLOAD = apiRequest -> {
        if (apiRequest instanceof MemberUploadRequest) {
            MemberUploadRequest request = (MemberUploadRequest) apiRequest;
            BizMemberUploadRequest bizRequest = new BizMemberUploadRequest();
            bizRequest.getExtendInfo().putAll(request.getExtendInfo());
            bizRequest.setScene(BizUploadScene.getByCode(request.getScene()));
            return bizRequest;
        }
        return null;
    };

    public static Handler<BizLocalAreaRequest> LOCAL_AREA = apiRequest -> {
        if (apiRequest instanceof LocalAreaRequest) {
            LocalAreaRequest request = (LocalAreaRequest) apiRequest;
            BizLocalAreaRequest bizRequest = new BizLocalAreaRequest();
            bizRequest.setAreaLevel(request.getAreaLevel());
            bizRequest.setAreaIds(request.getAreaIds());
            bizRequest.setParentIds(request.getParentIds());
            return bizRequest;
        }
        return null;
    };

    public static Handler<BizSubOrgCreateRequest> SUB_ORG_CREATE = apiRequest -> {
        if (apiRequest instanceof SubOrgCreateRequest) {
            SubOrgCreateRequest request = (SubOrgCreateRequest) apiRequest;
            BizSubOrgCreateRequest bizRequest = new BizSubOrgCreateRequest();
            bizRequest.setName(request.getName());
            bizRequest.getExtendInfo().putAll(request.getExtendInfo());
            return bizRequest;
        }
        return null;
    };

    public static BizRequest getBizRequest(ApiRequest apiRequest) {
        if (apiRequest != null) {
            BizRequest bizRequest = new BizRequest();
            bizRequest.getExtendInfo().putAll(apiRequest.getExtendInfo());
            return bizRequest;
        }
        return null;
    }

    public static BizPageRequest getBizPageRequest(ApiRequest apiRequest) {
        if (apiRequest instanceof ApiPageRequest) {
            ApiPageRequest request = (ApiPageRequest) apiRequest;
            BizPageRequest bizRequest = new BizPageRequest();
            if (request.getPageNumber() == null || request.getPageNumber() < 1) {
                bizRequest.setPageNumber(BizPageRequest.DEFAULT_PAGE_NUMBER);
            } else {
                bizRequest.setPageNumber(request.getPageNumber());
            }

            if (request.getPageSize() == null || request.getPageSize() < 1) {
                bizRequest.setPageSize(BizPageRequest.DEFAULT_PAGE_SIZE);
            } else {
                bizRequest.setPageSize(request.getPageSize());
            }
            bizRequest.setSearchKey(request.getSearchKey());
            bizRequest.getExtendInfo().putAll(request.getExtendInfo());
            return bizRequest;
        }
        return null;
    }

    public static BizDetailRequest getBizDetailRequest(ApiRequest apiRequest) {
        if (apiRequest instanceof ApiDetailRequest) {
            BizDetailRequest bizDetailRequest = new BizDetailRequest();
            bizDetailRequest.setDetailId(((ApiDetailRequest) apiRequest).getDetailId());
            bizDetailRequest.setRequestId(apiRequest.getExtendInfo().get("REQUEST_ID"));
            return bizDetailRequest;
        }
        return null;
    }

    public static BizAsyncTriggerRequest getBizAsyncTriggerRequest(ApiRequest apiRequest) {
        if (apiRequest instanceof AsyncTriggerRequest) {
            AsyncTriggerRequest request = (AsyncTriggerRequest) apiRequest;
            BizAsyncTriggerRequest bizRequest = new BizAsyncTriggerRequest();
            bizRequest.setScene(request.getScene());
            bizRequest.setTargetId(request.getTargetId());
            return bizRequest;
        }
        return null;
    }

    interface Handler<T extends BizRequest> {
        T convert(ApiRequest apiRequest);
    }
}
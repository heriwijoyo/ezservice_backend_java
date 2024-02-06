/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.core.converter;

import id.ezclouds.biz.arahindonesia.model.member.BizGender;
import id.ezclouds.biz.arahindonesia.service.request.*;
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

    public static BizMemberRegisterRequest convert(MemberRegisterRequest request) {
        if (request == null) { return null; }
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
        BizGender bizGender = BizGender.getByCode(request.getGender());
        if (bizGender != null) {
            bizRequest.setBizGender(bizGender);
        }

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

    interface Handler<T extends BizRequest> {
        T convert(ApiRequest apiRequest);
    }
}
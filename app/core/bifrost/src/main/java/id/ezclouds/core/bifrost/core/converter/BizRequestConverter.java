/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.core.converter;

import id.ezclouds.biz.arahindonesia.model.member.BizGender;
import id.ezclouds.biz.arahindonesia.service.request.BizMemberRegisterRequest;
import id.ezclouds.core.bifrost.app.api.request.MemberRegisterRequest;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizRequestConverter.java, v 0.1 2024‐01‐01 11:42 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizRequestConverter {

    public static BizMemberRegisterRequest convert(MemberRegisterRequest request) {
        if (request == null) { return null; }
        BizMemberRegisterRequest bizRequest = new BizMemberRegisterRequest();
        bizRequest.setReferrerId(request.getReferrerId());
        bizRequest.setRoles(request.getRoles());
        bizRequest.setName(request.getName());
        bizRequest.setNickname(request.getNickname());
        bizRequest.setDateOfBirth(request.getDateOfBirth());
        bizRequest.setPhone(request.getPhone());
        bizRequest.setEmail(request.getEmail());
        bizRequest.setAvatarUrl(request.getAvatarUrl());

        BizGender bizGender = BizGender.getByCode(request.getGender());
        if (bizGender != null) {
            bizRequest.setBizGender(bizGender);
        }

        return bizRequest;
    }
}
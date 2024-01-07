/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api.converter;

import id.ezclouds.biz.arahindonesia.model.member.MemberBase;
import id.ezclouds.core.bifrost.app.api.model.MemberInfo;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizResultConverter.java, v 0.1 2024‐01‐06 7:27 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizResultConverter {

    public static MemberInfo convert(MemberBase memberBase) {
        if (memberBase == null) { return null; }
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setMemberId(memberBase.getMemberId());
        memberInfo.setRoles(memberBase.getRole());
        return memberInfo;
    }
}
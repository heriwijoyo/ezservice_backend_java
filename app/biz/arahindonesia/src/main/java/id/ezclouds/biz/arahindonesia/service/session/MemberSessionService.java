/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service.session;

import id.ezclouds.biz.arahindonesia.model.authentication.AppMemberClient;
import id.ezclouds.biz.arahindonesia.model.session.MemberSession;
import id.ezclouds.biz.arahindonesia.service.data.MemberSessionDataService;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.common.util.error.EzErrorException;
import id.ezclouds.core.shared.context.EzAppContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: MemberSessionService.java, v 0.1 2023‐12‐11 11:40 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class MemberSessionService {

    private static final int SESSION_EXPIRY_DAYS = 3;

    @Autowired
    private MemberSessionDataService memberSessionDataService;

    public MemberSession createMemberSession(AppMemberClient memberClient) {
        String deviceId = EzAppContextHolder.getContext().getDeviceId();
        Date createdTime = new Date();
        Date expiryTime = DateUtil.getDateAfterDays(createdTime, SESSION_EXPIRY_DAYS);

        MemberSession memberSession = new MemberSession();
        memberSession.setSessionId(generateSessionId(memberClient));
        memberSession.setOrgId(memberClient.getOrgId());
        memberSession.setAppId(memberClient.getAppId());
        memberSession.setClientId(memberClient.getClientId());
        memberSession.setMemberId(memberClient.getMemberId());
        memberSession.setDeviceId(deviceId);
        memberSession.setCreatedTime(DateUtil.getFormattedDate(createdTime));
        memberSession.setExpiryTime(DateUtil.getFormattedDate(expiryTime));

        memberSessionDataService.insertMemberClientSession(memberSession);

        return memberSession;
    }

    public void validateMemberSession(MemberSession memberSession) throws EzErrorException {

    }

    public boolean softValidateMemberSession(MemberSession memberSession) {
        return false;
    }

    private String generateSessionId(AppMemberClient memberClient) {
        return HashUtil.createHash(memberClient.getOrgId(), memberClient.getMemberId(), DateUtil.getCurrentFormattedDate());
    }
}
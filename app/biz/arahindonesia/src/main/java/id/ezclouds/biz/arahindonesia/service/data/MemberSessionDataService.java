/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service.data;

import id.ezclouds.biz.arahindonesia.model.session.MemberSession;
import id.ezclouds.common.dal.repo.session.AppMemberClientSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: MemberSessionDataService.java, v 0.1 2023‐12‐12 12:14 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class MemberSessionDataService {

    @Autowired
    private AppMemberClientSessionRepository appMemberClientSessionRepository;

    @Transactional
    public void insertMemberClientSession(MemberSession memberSession) {
        appMemberClientSessionRepository
                .insertActiveSession(
                        memberSession.getSessionId(),
                        memberSession.getOrgId(),
                        memberSession.getAppId(),
                        memberSession.getClientId(),
                        memberSession.getMemberId(),
                        memberSession.getDeviceId(),
                        memberSession.getCreatedTime(),
                        memberSession.getExpiryTime()
                );
    }
}
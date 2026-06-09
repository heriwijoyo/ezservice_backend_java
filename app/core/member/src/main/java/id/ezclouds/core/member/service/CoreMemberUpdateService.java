/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.member.service;

import id.ezclouds.common.facade.dal.member.BizMemberBackOfficeDAO;
import id.ezclouds.common.facade.member.MemberUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreMemberUpdateService.java, v 0.1 2024‐08‐12 8:30 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreMemberUpdateService implements MemberUpdateService {

    @Autowired
    private BizMemberBackOfficeDAO bizMemberBackOfficeDAO;

    @Override
    @Transactional
    public void updateRoles(String memberId, String roles) {
        bizMemberBackOfficeDAO.updateRoles(memberId, roles);
    }
}
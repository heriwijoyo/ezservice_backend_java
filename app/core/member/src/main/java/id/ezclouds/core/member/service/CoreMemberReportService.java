/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.member.service;

import id.ezclouds.common.facade.dal.member.BizMemberReportDAO;
import id.ezclouds.common.facade.member.MemberReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreMemberReportService.java, v 0.1 2024‐08‐01 8:07 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreMemberReportService implements MemberReportService {

    @Autowired
    private BizMemberReportDAO bizMemberReportDAO;

    @Override
    public long countToday(String orgId) {
        return bizMemberReportDAO.countToday(orgId);
    }

    @Override
    public long countYesterday(String orgId) {
        return bizMemberReportDAO.countYesterday(orgId);
    }
}
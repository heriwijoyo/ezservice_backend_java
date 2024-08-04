/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.member;

import id.ezclouds.common.facade.dal.member.BizMemberReportDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.core.dal.member.repo.CoreMemberReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreMemberReportDAO.java, v 0.1 2024‐08‐01 8:09 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class CoreMemberReportDAO implements BizMemberReportDAO {

    @Autowired
    private CoreMemberReportRepository coreMemberReportRepository;

    @EzDAOLogger
    @Override
    public long countToday(String orgId) {
        Date today = new Date();
        String startDate = DateUtil.getFormattedDayStart(today);
        String endDate = DateUtil.getFormattedDayEnd(today);
        return coreMemberReportRepository
                .countByOrgIdWithinDate(orgId, startDate, endDate);
    }

    @EzDAOLogger
    @Override
    public long countYesterday(String orgId) {
        Date yesterday = DateUtil.getDateAfterDays(new Date(), -1);
        String startDate = DateUtil.getFormattedDayStart(yesterday);
        String endDate = DateUtil.getFormattedDayEnd(yesterday);
        return coreMemberReportRepository
                .countByOrgIdWithinDate(orgId, startDate, endDate);
    }
}
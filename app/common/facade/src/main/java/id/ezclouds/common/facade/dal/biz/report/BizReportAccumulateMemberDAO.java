/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal.biz.report;

import id.ezclouds.common.model.biz.report.BizReportAccumulateMember;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportAccumulateMemberDAO.java, v 0.1 2024‐10‐10 2:52 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizReportAccumulateMemberDAO {

    BizReportAccumulateMember getAndLock(String accumulateId);

    void store(BizReportAccumulateMember accumulateMember);
}
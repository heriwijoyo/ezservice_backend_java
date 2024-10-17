/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.biz;

import id.ezclouds.common.facade.dal.member.BizMemberUnionDAO;
import id.ezclouds.common.facade.dal.organization.SubOrganizationDAO;
import id.ezclouds.common.facade.member.MemberReportService;
import id.ezclouds.common.model.report.BizReportOverallKey;
import id.ezclouds.core.process.biz.inner.BizInnerProcessReportOverall;
import id.ezclouds.core.process.model.BizProcessEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizProcessorReportGenerateOverall.java, v 0.1 2024‐07‐28 4:38 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizProcessorReportGenerateOverall extends BizAsyncProcessor {

    @Autowired
    private BizMemberUnionDAO bizMemberUnionDAO;

    @Autowired
    private SubOrganizationDAO subOrganizationDAO;

    @Autowired
    private BizInnerProcessReportOverall bizInnerProcessReportOverall;

    @Autowired
    private MemberReportService memberReportService;

    @Override
    public BizProcessEvent getProcessEvent() {
        return BizProcessEvent.GENERATE_REPORT_OVERALL;
    }

    @Override
    protected int maxProcessTime() {
        return 1 * 60 * 1000;
    }

    @Override
    protected boolean onProcess(Object request, List<String> logData) {
        String orgId = (String) request;
        logData.add("ORG_ID="+ orgId);

        long totalMember = bizMemberUnionDAO.countByOrgId(orgId);
        logData.add("TOTAL_MEMBER=" + totalMember);
        bizInnerProcessReportOverall.storeReport(orgId, BizReportOverallKey.TOTAL_MEMBER_UNION.getCode(), (int)totalMember);

        long totalSubOrg = subOrganizationDAO.countByOrgId(orgId) - 1;
        logData.add("TOTAL_SUB_ORG=" + totalSubOrg);
        bizInnerProcessReportOverall.storeReport(orgId, BizReportOverallKey.TOTAL_SUB_ORGANIZATION.getCode(), (int)totalSubOrg);

        long memberYesterday = memberReportService.countYesterday(orgId);
        logData.add("MEMBER_YESTERDAY="+ memberYesterday);
        bizInnerProcessReportOverall.storeReport(orgId, BizReportOverallKey.MEMBER_YESTERDAY.getCode(), (int)memberYesterday);

        return true;
    }
}
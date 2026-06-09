/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.report;

import id.ezclouds.common.facade.dal.biz.election.BizVoterDAO;
import id.ezclouds.common.facade.dal.organization.SubOrganizationDAO;
import id.ezclouds.common.facade.integration.EzConnectService;
import id.ezclouds.common.model.core.organization.SubOrganization;
import id.ezclouds.common.model.integration.WhatsappSendRequest;
import id.ezclouds.common.model.query.BizGroupQueryCount;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.core.process.biz.BizAsyncProcessor;
import id.ezclouds.core.process.model.BizProcessEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizProcessReportDailyClusterPerformance.java, v 0.1 2024‐11‐07 10:09 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizProcessReportDailyClusterPerformance extends BizAsyncProcessor {

    @Autowired
    private SubOrganizationDAO subOrganizationDAO;

    @Autowired
    private BizVoterDAO bizVoterDAO;

    @Autowired
    private EzConnectService ezConnectService;

    @Override
    public BizProcessEvent getProcessEvent() {
        return BizProcessEvent.REPORT_DAILY_CLUSTER_PERFORMANCE;
    }

    @Override
    protected int maxProcessTime() {
        return 60 * 1000;
    }

    @Override
    protected boolean onProcess(Object request, List<String> logData) {
        String param = (String) request;
        String orgId = param.split(",")[0];
        int nDays = Integer.parseInt(param.split(",")[1]);
        String reportMessage = fetchLastNDaysReport(orgId, nDays);

        List<String> reportPhoneReceiver = Arrays.asList("6281281150355", "6285310197009", "6281328602519");
        for (String phone : reportPhoneReceiver) {
            WhatsappSendRequest sendRequest = new WhatsappSendRequest();
            sendRequest.setOrgId(orgId);
            sendRequest.setPhoneNumber(phone);
            sendRequest.setMessage(reportMessage);

            ezConnectService.sendWhatsappMessage(sendRequest);
        }

        return true;
    }

    private String fetchLastNDaysReport(String orgId, int nDays) {
        List<SubOrganization> subOrgs = subOrganizationDAO.getByOrgId(orgId);
        Date today = new Date();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("Report per Komunitas ")
                .append(nDays)
                .append(" hari terakhir:\n\n");

        for (int i = 1; i <= nDays; i++) {
            Date reportDate = DateUtil.getDateAfterDays(today, -i);
            String startTime = DateUtil.getFormattedDayStart(reportDate);
            String endTime = DateUtil.getFormattedDayEnd(reportDate);

            stringBuilder
                    .append("*")
                    .append(DateUtil.getFormattedDate(reportDate, DateUtil.FORMAT_DATE))
                    .append("*\n");

            List<BizGroupQueryCount> groupQueryCounts = bizVoterDAO
                    .countGroupBySubOrgWithinDate(orgId, startTime, endTime);
            for (BizGroupQueryCount groupQueryCount : groupQueryCounts) {
                String subOrgName = getSubOrgName(subOrgs, groupQueryCount.getGroupId());
                stringBuilder
                        .append("- ")
                        .append(subOrgName)
                        .append(" = ")
                        .append(groupQueryCount.getGroupCount())
                        .append("\n");
            }
        }
        return stringBuilder.toString();
    }

    private String getSubOrgName(List<SubOrganization> subOrgs, String subOrgId) {
        for (SubOrganization subOrganization : subOrgs) {
            if (subOrganization.getSubOrgId().equals(subOrgId)) {
                return subOrganization.getName();
            }
        }
        return "Undefined";
    }
}
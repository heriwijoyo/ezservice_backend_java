/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.processor;

import id.ezclouds.biz.ezservice.enums.BizConnectType;
import id.ezclouds.biz.ezservice.service.processor.event.BizProcessEvent;
import id.ezclouds.biz.ezservice.service.inner.service.BizConnectInnerService;
import id.ezclouds.biz.ezservice.subbiz.arahindonesia.dataobject.BizSubOrganizationDO;
import id.ezclouds.biz.ezservice.subbiz.arahindonesia.repo.AppSubOrganizationRepository;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.core.member.service.CoreMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizSubOrgDailyMonitorProcessor.java, v 0.1 2024‐07‐18 6:06 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
@Async
public class BizSubOrgDailyMonitorProcessor extends BizAsyncProcessor {

    @Autowired
    private AppSubOrganizationRepository appSubOrganizationRepository;

    @Autowired
    private CoreMemberService coreMemberService;

    @Autowired
    private BizConnectInnerService bizConnectInnerService;

    @Override
    public BizProcessEvent getProcessEvent() {
        return BizProcessEvent.SUB_ORG_DAILY_MONITOR;
    }

    @Override
    protected int maxProcessTime() {
        return 5 * 60 * 1000;
    }

    @Override
    protected boolean onProcess(Object request, List<String> logData) {
        final String orgId = (String) request;
        logData.add("ORG_ID="+ orgId);

        List<String> reportPhoneReceiver = Arrays.asList("6281281150355", "6285310197009", "6281328602519");
        String reportMessage = fetchLastNDaysReport(orgId, 5);
        for (String reportReceiver : reportPhoneReceiver) {
            bizConnectInnerService
                    .sendMessage(BizConnectType.WHATSAPP, orgId, reportReceiver, reportMessage);
        }
        return true;
    }

    private String fetchLastNDaysReport(String orgId, int nDays) {
        List<BizSubOrganizationDO> subOrgs = appSubOrganizationRepository
                .findByOrgId(orgId);
        Date today = new Date();

        String resultMsg = "Report per Komunitas "+ nDays +" hari terakhir:\n\n";
        for (int i = 1; i <= nDays; i++) {
            Date reportDate = DateUtil.getDateAfterDays(today, -i);
            String startTime = DateUtil.getFormattedDayStart(reportDate);
            String endTime = DateUtil.getFormattedDayEnd(reportDate);

            resultMsg += "*"+ DateUtil.getFormattedDate(reportDate, DateUtil.FORMAT_DATE) + "*\n";

            Map<String, Long> result = coreMemberService
                    .getGroupCountBySubOrg(orgId, startTime, endTime);
            if (result.size() > 0) {
                for (Map.Entry<String, Long> entry : result.entrySet()) {
                    String subOrgName = getSubOrgName(subOrgs, entry.getKey());
                    resultMsg += "- "+ subOrgName +" = "+ entry.getValue() + "\n";
                }
            } else {
                resultMsg = "- TIDAK ADA DATA";
            }
        }
        return resultMsg;
    }

    private String getSubOrgName(List<BizSubOrganizationDO> subOrgs, String subOrgId) {
        for (BizSubOrganizationDO subOrganization : subOrgs) {
            if (subOrganization.getSubOrgId().equals(subOrgId)) {
                return subOrganization.getName();
            }
        }
        return "Undefine";
    }
}
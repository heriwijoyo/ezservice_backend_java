/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.processor;

import id.ezclouds.biz.ezservice.enums.BizConnectType;
import id.ezclouds.biz.ezservice.service.async.BizThreadSharedResource;
import id.ezclouds.biz.ezservice.service.async.event.BizProcessEvent;
import id.ezclouds.biz.ezservice.service.inner.service.BizConnectInnerService;
import id.ezclouds.biz.ezservice.service.template.BizProcessTemplate;
import id.ezclouds.biz.ezservice.subbiz.arahindonesia.dataobject.BizSubOrganizationDO;
import id.ezclouds.biz.ezservice.subbiz.arahindonesia.repo.AppSubOrganizationRepository;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.core.member.service.CoreMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizSubOrgDailyMonitorProcessor.java, v 0.1 2024‐07‐18 6:06 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
@Async
public class BizSubOrgDailyMonitorProcessor {

    @Autowired
    private BizThreadSharedResource bizThreadSharedResource;

    @Autowired
    private AppSubOrganizationRepository appSubOrganizationRepository;

    @Autowired
    private CoreMemberService coreMemberService;

    @Autowired
    private BizConnectInnerService bizConnectInnerService;

    public void process(String orgId) {
        final List<String> logData = new ArrayList<>();
        logData.add("ORG_ID="+ orgId);

        BizProcessTemplate.execute(BizProcessEvent.SUB_ORG_DAILY_MONITOR, new BizProcessTemplate.Handler() {
            @Override
            public boolean onProcess(BizProcessEvent processEvent) {
                bizThreadSharedResource.startProcess(processEvent.getEventCode());

                List<BizSubOrganizationDO> subOrgs = appSubOrganizationRepository
                        .findByOrgId(orgId);

                Date yesterday = DateUtil.getDateAfterDays(new Date(), -1);
                String startTime = DateUtil.getFormattedDayStart(yesterday);
                String endTime = DateUtil.getFormattedDayEnd(yesterday);
                logData.add("START="+ startTime);
                logData.add("END="+ endTime);

                Map<String, Long> result = coreMemberService
                        .getGroupCountBySubOrg(orgId, startTime, endTime);

                String resultMsg = StringUtil.EMPTY;
                if (result.size() > 0) {
                    for (Map.Entry<String, Long> entry : result.entrySet()) {
                        String subOrgName = getSubOrgName(subOrgs, entry.getKey());
                        resultMsg += "- "+ subOrgName +" = "+ entry.getValue() + "\n";
                    }
                } else {
                    resultMsg = "TIDAK ADA DATA";
                }

                String reportDate = DateUtil.getFormattedDate(yesterday, DateUtil.FORMAT_DATE);
                String reportMessage = "Report Komunitas "+ reportDate + ":\n\n" + resultMsg;

                bizConnectInnerService.sendMessage(BizConnectType.WHATSAPP, orgId, "6281281150355", reportMessage);

                logData.add("RESULT="+ result.size());
                return true;
            }

            @Override
            public void onFinish() {
                bizThreadSharedResource.stopProcess();
            }

            @Override
            public List<String> getLogData() {
                return logData;
            }
        });
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
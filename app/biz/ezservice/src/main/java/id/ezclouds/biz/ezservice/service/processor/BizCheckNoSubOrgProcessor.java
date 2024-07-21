/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.processor;

import id.ezclouds.biz.ezservice.enums.BizConnectType;
import id.ezclouds.biz.ezservice.service.processor.shared.BizThreadSharedResource;
import id.ezclouds.biz.ezservice.service.processor.event.BizProcessEvent;
import id.ezclouds.biz.ezservice.service.inner.service.BizConnectInnerService;
import id.ezclouds.biz.ezservice.service.template.BizProcessTemplate;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.core.member.service.CoreMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizCheckNoSubOrgProcessor.java, v 0.1 2024‐07‐20 1:06 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
@Async
public class BizCheckNoSubOrgProcessor {

    @Autowired
    private BizThreadSharedResource bizThreadSharedResource;

    @Autowired
    private CoreMemberService coreMemberService;

    @Autowired
    private BizConnectInnerService bizConnectInnerService;

    public void process(String orgId) {
        final List<String> logData = new ArrayList<>();
        logData.add("ORG_ID="+ orgId);

        String reportReceiver = "6281281150355";
        BizProcessTemplate.execute(BizProcessEvent.DAILY_CHECK_NO_SUB_ORG, new BizProcessTemplate.Handler() {
            @Override
            public void doStart(BizProcessEvent processEvent) {
            }

            @Override
            public boolean doProcess(BizProcessEvent processEvent) {
                bizThreadSharedResource.startProcess(processEvent.getEventCode());

                Date yesterday = DateUtil.getDateAfterDays(new Date(), -1);
                String startTime = DateUtil.getFormattedDate(yesterday, DateUtil.FORMAT_DATETIME_DEFAULT);
                Map<String, Long> result = coreMemberService
                        .getEmptyGroupCountByReferrerId(orgId, startTime);

                String reportDate = DateUtil.getFormattedDate(yesterday, DateUtil.FORMAT_DATE);
                String reportMsg = "Referrer without SubOrg ("+ reportDate +"):\n";
                if (result.size() < 1) {
                    reportMsg += "- TIDAK ADA";
                } else {
                    List<String> referrerIds = new ArrayList<>();
                    for (Map.Entry<String, Long> entry : result.entrySet()) {
                        referrerIds.add(entry.getKey());
                    }

                    Map<String, String> memberNamesMap = coreMemberService
                            .getMemberNamesMap(referrerIds);
                    for (Map.Entry<String, String> namesEntry : memberNamesMap.entrySet()) {
                        reportMsg += namesEntry.getValue() +" : "+ result.get(namesEntry.getKey()) +"\n";
                    }
                }

                bizConnectInnerService.sendMessage(
                        BizConnectType.WHATSAPP,
                        orgId,
                        reportReceiver,
                        reportMsg
                );

                return true;
            }

            @Override
            public void doFinish(BizProcessEvent processEvent) {
                bizThreadSharedResource.stopProcess();
            }

            @Override
            public List<String> getLogData() {
                return logData;
            }
        });
    }
}
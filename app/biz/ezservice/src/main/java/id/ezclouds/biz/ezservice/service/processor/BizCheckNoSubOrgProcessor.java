/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.processor;

import id.ezclouds.biz.ezservice.enums.BizConnectType;
import id.ezclouds.biz.ezservice.service.processor.event.BizProcessEvent;
import id.ezclouds.biz.ezservice.service.inner.service.BizConnectInnerService;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.core.member.service.CoreMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizCheckNoSubOrgProcessor.java, v 0.1 2024‐07‐20 1:06 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
@Scope(value = "prototype")
@Async
public class BizCheckNoSubOrgProcessor extends BizAsyncProcessor {

    @Autowired
    private CoreMemberService coreMemberService;

    @Autowired
    private BizConnectInnerService bizConnectInnerService;

    @Override
    public BizProcessEvent getProcessEvent() {
        return BizProcessEvent.DAILY_CHECK_NO_SUB_ORG;
    }

    @Override
    protected int maxProcessTime() {
        return 5 * 60 * 1000;
    }

    @Override
    protected boolean onProcess(Object request, List<String> logData) {
        final String orgId = (String) request;
        logData.add("ORG_ID="+ orgId);

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
                "6281281150355",
                reportMsg
        );
        return true;
    }
}
/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.biz.service.report;

import id.ezclouds.common.model.broker.event.EzCommonEvent;
import id.ezclouds.common.model.broker.topic.EzCoreTopic;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportAccumulateProcessor.java, v 0.1 2024‐10‐02 1:29 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizReportAccumulateProcessor {

    private static List<EzCoreTopic> allowedTopics = Arrays.asList(
            EzCoreTopic.ELECTION_CANVASS_RECORD_ADD,
            EzCoreTopic.ELECTION_VOTER_REGISTER,
            EzCoreTopic.ELECTION_QUICK_COUNT_SUBMIT,
            EzCoreTopic.ELECTION_QUICK_COUNT_VERIFY
    );

    @Async
    @EventListener
    public void handleEventForReport(EzCommonEvent ezCommonEvent) {
        boolean isOnFilter = allowedTopics.contains(ezCommonEvent.getCoreTopic());
        if (!isOnFilter) {
            return;
        }

        switch (ezCommonEvent.getCoreTopic()) {
            case ELECTION_VOTER_REGISTER:
                break;
        }
    }
}
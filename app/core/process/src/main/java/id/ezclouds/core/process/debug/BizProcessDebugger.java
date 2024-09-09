/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.debug;

import id.ezclouds.common.facade.broker.BrokerDataExchangeService;
import id.ezclouds.common.facade.integration.BizObjectMapperService;
import id.ezclouds.common.model.broker.BrokerDataTopic;
import id.ezclouds.common.model.report.BizReportOverallKey;
import id.ezclouds.core.process.biz.BizAsyncProcessor;
import id.ezclouds.core.process.model.BizProcessEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizProcessDebugger.java, v 0.1 2024‐09‐09 12:49 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizProcessDebugger extends BizAsyncProcessor {

    @Autowired
    private BrokerDataExchangeService brokerDataExchangeService;

    @Autowired
    private BizObjectMapperService bizObjectMapperService;

    @Override
    protected int maxProcessTime() {
        return 10 * 60 * 1000;
    }

    @Override
    protected boolean onProcess(Object request, List<String> logData) {
        Map<String, String> data = new HashMap<>();
        data.put(BizReportOverallKey.BG_REGENCY.getCode(), "74");
        data.put(BizReportOverallKey.BG_DISTRICT.getCode(), "403");

        brokerDataExchangeService.emitEvent(BrokerDataTopic.BIZ_REPORT_OVERALL, data);
        return false;
    }

    @Override
    public BizProcessEvent getProcessEvent() {
        return BizProcessEvent.BIZ_DEBUGGER;
    }
}
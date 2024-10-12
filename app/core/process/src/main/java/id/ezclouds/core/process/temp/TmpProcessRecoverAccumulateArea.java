/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.temp;

import id.ezclouds.common.facade.broker.CoreEventPublisherService;
import id.ezclouds.common.facade.dal.biz.report.BizReportAccumulateProcessDAO;
import id.ezclouds.common.facade.integration.BizObjectMapperService;
import id.ezclouds.common.model.biz.election.BizVoter;
import id.ezclouds.common.model.biz.election.BizVoterInvalid;
import id.ezclouds.common.model.biz.report.BizReportAccumulateProcess;
import id.ezclouds.common.model.biz.report.RecoverBizVoterAccumulateArea;
import id.ezclouds.common.model.broker.event.EzCommonEvent;
import id.ezclouds.common.model.broker.topic.EzCoreTopic;
import id.ezclouds.core.process.biz.BizAsyncProcessor;
import id.ezclouds.core.process.model.BizProcessEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: TmpProcessRecoverAccumulateArea.java, v 0.1 2024‐10‐12 5:42 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class TmpProcessRecoverAccumulateArea extends BizAsyncProcessor {

    @Autowired
    private BizReportAccumulateProcessDAO bizReportAccumulateProcessDAO;

    @Autowired
    private BizObjectMapperService bizObjectMapperService;

    @Autowired
    private CoreEventPublisherService coreEventPublisherService;

    @Override
    public BizProcessEvent getProcessEvent() {
        return BizProcessEvent.TMP_RECOVER_ACCUMULATE_AREA;
    }

    @Override
    protected int maxProcessTime() {
        return 10 * 60 * 1000;
    }

    @Override
    protected boolean onProcess(Object request, List<String> logData) {
        String param = (String) request;
        String orgId = param.split(",")[0];
        String size = param.split(",")[1];
        logData.add("ORG_ID="+ orgId);

        List<BizReportAccumulateProcess> accumulateProcesses = bizReportAccumulateProcessDAO
                .getFailedProcess(orgId, Integer.parseInt(size));

        for (BizReportAccumulateProcess accumulateProcess : accumulateProcesses) {
            RecoverBizVoterAccumulateArea recoverData = new RecoverBizVoterAccumulateArea(accumulateProcess.getProcessId(), accumulateProcess.getTopic());
            if (accumulateProcess.getTopic() == EzCoreTopic.ELECTION_VOTER_REGISTER) {
                BizVoter bizVoter = bizObjectMapperService.parseJson(accumulateProcess.getPayload(), BizVoter.class);
                recoverData.setBizVoter(bizVoter);
            } else {
                BizVoterInvalid bizVoterInvalid = bizObjectMapperService.parseJson(accumulateProcess.getPayload(), BizVoterInvalid.class);
                recoverData.setBizVoterInvalid(bizVoterInvalid);
            }

            coreEventPublisherService.publish(new EzCommonEvent(EzCoreTopic.BIZ_REPORT_RECOVER_ACCUMULATE_VOTER, orgId, recoverData));

            try {
                Thread.sleep(200);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return true;
    }
}
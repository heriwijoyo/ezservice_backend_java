/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz.converter;

import id.ezclouds.common.model.biz.report.BizReportAccumulateProcess;
import id.ezclouds.common.model.broker.topic.EzCoreTopic;
import id.ezclouds.common.model.converter.CommonDOModelConverter;
import id.ezclouds.common.model.process.ProcessStatus;
import id.ezclouds.core.dal.biz.dataobject.BizReportAccumulateProcessDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportAccumulateProcessConverter.java, v 0.1 2024‐10‐02 2:48 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizReportAccumulateProcessConverter extends CommonDOModelConverter<BizReportAccumulateProcessDO, BizReportAccumulateProcess> {

    @Override
    protected BizReportAccumulateProcess safeConvertQuery(BizReportAccumulateProcessDO dataObject) {
        BizReportAccumulateProcess accumulateProcess = new BizReportAccumulateProcess();
        accumulateProcess.setProcessId(dataObject.getProcessId());
        accumulateProcess.setOrgId(dataObject.getOrgId());
        accumulateProcess.setTopic(EzCoreTopic.getByCode(dataObject.getTopic()));
        accumulateProcess.setPayload(dataObject.getPayload());
        accumulateProcess.setStatus(ProcessStatus.getByCode(dataObject.getStatus()));
        accumulateProcess.setCreatedTime(dataObject.getCreatedTime());
        accumulateProcess.setFinishedTime(dataObject.getFinishedTime());
        return accumulateProcess;
    }

    @Override
    protected BizReportAccumulateProcessDO safeConvertStore(BizReportAccumulateProcess model) {
        BizReportAccumulateProcessDO processDO = new BizReportAccumulateProcessDO();
        processDO.setProcessId(model.getProcessId());
        processDO.setOrgId(model.getOrgId());
        processDO.setTopic(model.getTopic().getCode());
        processDO.setPayload(model.getPayload());
        processDO.setStatus(model.getStatus().getCode());
        processDO.setCreatedTime(model.getCreatedTime());
        processDO.setFinishedTime(model.getFinishedTime());
        return processDO;
    }
}
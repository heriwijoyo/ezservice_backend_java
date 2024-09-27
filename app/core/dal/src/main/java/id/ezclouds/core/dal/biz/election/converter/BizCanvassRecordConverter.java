/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz.election.converter;

import id.ezclouds.common.model.biz.election.BizCanvassRecord;
import id.ezclouds.common.model.converter.CommonDOModelConverter;
import id.ezclouds.core.dal.biz.election.dataobject.BizCanvassRecordDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizCanvassRecordConverter.java, v 0.1 2024‐09‐26 12:41 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizCanvassRecordConverter extends CommonDOModelConverter<BizCanvassRecordDO, BizCanvassRecord> {

    @Override
    protected BizCanvassRecord safeConvertQuery(BizCanvassRecordDO dataObject) {
        BizCanvassRecord canvassOrder = new BizCanvassRecord();
        canvassOrder.setCanvassOrderId(dataObject.getCanvassOrderId());
        canvassOrder.setOrgId(dataObject.getOrgId());
        canvassOrder.setBizSeqCode(dataObject.getBizSeqCode());
        canvassOrder.setVoterId(dataObject.getVoterId());
        canvassOrder.setReferrerId(dataObject.getReferrerId());
        canvassOrder.setFirstVisitDate(dataObject.getFirstVisitDate());
        canvassOrder.setFirstVisitAssessment(dataObject.getFirstVisitAssessment());
        canvassOrder.setSecondVisitDate(dataObject.getSecondVisitDate());
        canvassOrder.setSecondVisitAssessment(dataObject.getSecondVisitAssessment());
        canvassOrder.setThirdVisitDate(dataObject.getThirdVisitDate());
        canvassOrder.setThirdVisitAssessment(dataObject.getThirdVisitAssessment());
        canvassOrder.setCreatedTime(dataObject.getCreatedTime());
        canvassOrder.setModifiedTime(dataObject.getModifiedTime());
        return canvassOrder;
    }

    @Override
    protected BizCanvassRecordDO safeConvertStore(BizCanvassRecord model) {
        BizCanvassRecordDO canvassOrder = new BizCanvassRecordDO();
        canvassOrder.setCanvassOrderId(model.getCanvassOrderId());
        canvassOrder.setOrgId(model.getOrgId());
        canvassOrder.setBizSeqCode(model.getBizSeqCode());
        canvassOrder.setVoterId(model.getVoterId());
        canvassOrder.setReferrerId(model.getReferrerId());
        canvassOrder.setFirstVisitDate(model.getFirstVisitDate());
        canvassOrder.setFirstVisitAssessment(model.getFirstVisitAssessment());
        canvassOrder.setSecondVisitDate(model.getSecondVisitDate());
        canvassOrder.setSecondVisitAssessment(model.getSecondVisitAssessment());
        canvassOrder.setThirdVisitDate(model.getThirdVisitDate());
        canvassOrder.setThirdVisitAssessment(model.getThirdVisitAssessment());
        canvassOrder.setCreatedTime(model.getCreatedTime());
        canvassOrder.setModifiedTime(model.getModifiedTime());
        return canvassOrder;
    }
}
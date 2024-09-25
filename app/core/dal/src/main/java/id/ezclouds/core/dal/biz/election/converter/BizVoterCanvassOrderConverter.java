/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz.election.converter;

import id.ezclouds.common.model.biz.election.BizCanvassOrder;
import id.ezclouds.common.model.converter.CommonDOModelConverter;
import id.ezclouds.core.dal.biz.election.dataobject.BizVoterCanvassOrderDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizVoterCanvassOrderConverter.java, v 0.1 2024‐09‐26 12:41 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizVoterCanvassOrderConverter extends CommonDOModelConverter<BizVoterCanvassOrderDO, BizCanvassOrder> {

    @Override
    protected BizCanvassOrder safeConvertQuery(BizVoterCanvassOrderDO dataObject) {
        BizCanvassOrder canvassOrder = new BizCanvassOrder();
        canvassOrder.setCanvassOrderId(dataObject.getCanvassOrderId());
        canvassOrder.setOrgId(dataObject.getOrgId());
        canvassOrder.setSequenceCode(dataObject.getSequenceCode());
        canvassOrder.setShard(dataObject.getShard());
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
    protected BizVoterCanvassOrderDO safeConvertStore(BizCanvassOrder model) {
        BizVoterCanvassOrderDO canvassOrder = new BizVoterCanvassOrderDO();
        canvassOrder.setCanvassOrderId(model.getCanvassOrderId());
        canvassOrder.setOrgId(model.getOrgId());
        canvassOrder.setSequenceCode(model.getSequenceCode());
        canvassOrder.setShard(model.getShard());
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
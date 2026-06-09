/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz.converter;

import id.ezclouds.common.model.biz.report.BizAccumulateMemberKey;
import id.ezclouds.common.model.biz.report.BizReportAccumulateMember;
import id.ezclouds.common.model.converter.CommonDOModelConverter;
import id.ezclouds.core.dal.biz.dataobject.BizReportAccumulateMemberDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportAccumulateMemberConverter.java, v 0.1 2024‐10‐10 2:58 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizReportAccumulateMemberConverter extends CommonDOModelConverter<BizReportAccumulateMemberDO, BizReportAccumulateMember> {

    @Override
    protected BizReportAccumulateMember safeConvertQuery(BizReportAccumulateMemberDO dataObject) {
        BizReportAccumulateMember accumulateMember = new BizReportAccumulateMember();
        accumulateMember.setAccumulateMemberId(dataObject.getAccumulateMemberId());
        accumulateMember.setOrgId(dataObject.getOrgId());
        accumulateMember.setMemberId(dataObject.getMemberId());
        accumulateMember.setAccumulateKey(BizAccumulateMemberKey.getByKey(dataObject.getAccumulateKey()));
        accumulateMember.setAccumulateVariable(dataObject.getAccumulateVariable());
        accumulateMember.setAccumulateCount(dataObject.getAccumulateCount());
        accumulateMember.setModifiedTime(dataObject.getModifiedTime());
        return accumulateMember;
    }

    @Override
    protected BizReportAccumulateMemberDO safeConvertStore(BizReportAccumulateMember model) {
        BizReportAccumulateMemberDO accumulateMemberDO = new BizReportAccumulateMemberDO();
        accumulateMemberDO.setAccumulateMemberId(model.getAccumulateMemberId());
        accumulateMemberDO.setOrgId(model.getOrgId());
        accumulateMemberDO.setMemberId(model.getMemberId());
        accumulateMemberDO.setAccumulateKey(model.getAccumulateKey().getKey());
        accumulateMemberDO.setAccumulateVariable(model.getAccumulateVariable());
        accumulateMemberDO.setAccumulateCount(model.getAccumulateCount());
        accumulateMemberDO.setModifiedTime(model.getModifiedTime());
        return accumulateMemberDO;
    }
}
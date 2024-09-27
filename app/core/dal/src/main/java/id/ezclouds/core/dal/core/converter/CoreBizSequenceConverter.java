/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.core.converter;

import id.ezclouds.common.model.converter.CommonDOModelConverter;
import id.ezclouds.common.model.core.CoreBizSequence;
import id.ezclouds.core.dal.core.dataobject.EzCoreBizSequenceDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreBizSequenceConverter.java, v 0.1 2024‐09‐28 2:02 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CoreBizSequenceConverter extends CommonDOModelConverter<EzCoreBizSequenceDO, CoreBizSequence> {

    @Override
    protected CoreBizSequence safeConvertQuery(EzCoreBizSequenceDO dataObject) {
        CoreBizSequence bizSequence = new CoreBizSequence();
        bizSequence.setBizSeqId(dataObject.getBizSeqId());
        bizSequence.setOrgId(dataObject.getOrgId());
        bizSequence.setSeqBizKey(dataObject.getSeqBizKey());
        bizSequence.setSequence(dataObject.getSequence());
        bizSequence.setModifiedTime(dataObject.getModifiedTime());
        return bizSequence;
    }

    @Override
    protected EzCoreBizSequenceDO safeConvertStore(CoreBizSequence model) {
        EzCoreBizSequenceDO bizSequenceDO = new EzCoreBizSequenceDO();
        bizSequenceDO.setBizSeqId(model.getBizSeqId());
        bizSequenceDO.setOrgId(model.getOrgId());
        bizSequenceDO.setSeqBizKey(model.getSeqBizKey());
        bizSequenceDO.setSequence(model.getSequence());
        bizSequenceDO.setModifiedTime(model.getModifiedTime());
        return bizSequenceDO;
    }
}
/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.core.converter;

import id.ezclouds.common.model.converter.CommonDOModelConverter;
import id.ezclouds.common.model.core.CoreSeqSceneEnum;
import id.ezclouds.common.model.core.CoreSequence;
import id.ezclouds.core.dal.core.dataobject.EzCoreSequenceDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreSequenceConverter.java, v 0.1 2024‐09‐22 7:05 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CoreSequenceConverter extends CommonDOModelConverter<EzCoreSequenceDO, CoreSequence> {

    @Override
    protected CoreSequence safeConvertQuery(EzCoreSequenceDO dataObject) {
        CoreSequence sequence = new CoreSequence();
        sequence.setSequenceId(dataObject.getSequenceId());
        sequence.setOrgId(dataObject.getOrgId());
        sequence.setSeqScene(CoreSeqSceneEnum.getByScene(dataObject.getScene()));
        sequence.setStepMin(dataObject.getStepMin());
        sequence.setStepMax(dataObject.getStepMax());
        sequence.setStepValue(dataObject.getStepValue());
        sequence.setSeqLength(dataObject.getSeqLength());
        sequence.setSequence(dataObject.getSequence());
        return sequence;
    }

    @Override
    protected EzCoreSequenceDO safeConvertStore(CoreSequence model) {
        EzCoreSequenceDO sequenceDO = new EzCoreSequenceDO();
        sequenceDO.setSequenceId(model.getSequenceId());
        sequenceDO.setOrgId(model.getOrgId());
        sequenceDO.setScene(model.getSeqScene().getScene());
        sequenceDO.setSceneCode(model.getSeqScene().getSceneCode());
        sequenceDO.setStepMin(model.getStepMin());
        sequenceDO.setStepMax(model.getStepMax());
        sequenceDO.setStepValue(model.getStepValue());
        sequenceDO.setSeqLength(model.getSeqLength());
        sequenceDO.setSequence(model.getSequence());
        return sequenceDO;
    }
}
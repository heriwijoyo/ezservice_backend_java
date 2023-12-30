/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.shared.converter;

import id.ezclouds.core.shared.model.EzCoreSequence;
import id.ezclouds.core.shared.repo.dataobject.EzCoreSequenceDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzCoreConverter.java, v 0.1 2023‐12‐30 3:52 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class EzCoreConverter {

    public static EzCoreSequence convert(EzCoreSequenceDO sequenceDO) {
        EzCoreSequence sequence = new EzCoreSequence();
        sequence.setId(sequenceDO.getId());
        sequence.setOrgId(sequenceDO.getOrgId());
        sequence.setScene(sequenceDO.getScene());
        sequence.setStepMin(sequenceDO.getStepMin());
        sequence.setStepMax(sequenceDO.getStepMax());
        sequence.setStepValue(sequenceDO.getStepValue());
        sequence.setSequence(sequenceDO.getSequence());
        return sequence;
    }
}
/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.shared.service;

import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.error.EzErrorCode;
import id.ezclouds.core.shared.converter.EzCoreConverter;
import id.ezclouds.core.shared.model.EzCoreSequence;
import id.ezclouds.core.shared.repo.EzCoreSequenceRepository;
import id.ezclouds.core.shared.repo.dataobject.EzCoreSequenceDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreSequenceService.java, v 0.1 2023‐12‐30 3:47 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreSequenceService {

    @Autowired
    private EzCoreSequenceRepository ezCoreSequenceRepository;

    public String generateSequence(String orgId, String scene) {
        EzCoreSequenceDO ezCoreSequenceDO = ezCoreSequenceRepository.findForUpdateByOrgAndScene(orgId, scene);

        AssertUtil.notNull(ezCoreSequenceDO, EzErrorCode.CORE_SEQUENCE_ERROR, "EzCoreSequenceDO is null");

        EzCoreSequence currentSequence = EzCoreConverter.convert(ezCoreSequenceDO);
        EzCoreSequence newSequence = new EzCoreSequence();
        newSequence.setOrgId(currentSequence.getOrgId());
        newSequence.setScene(currentSequence.getScene());

        int nextStep;
        int nextSequence = currentSequence.getSequence();
        if (currentSequence.getStepValue() >= currentSequence.getStepMax()) {
            nextStep = currentSequence.getStepMin();
            nextSequence++;
        } else {
            nextStep = currentSequence.getStepValue() + 1;
        }

        ezCoreSequenceRepository.updateEzCoreSequence(currentSequence.getId(), nextStep, nextSequence);

        return String.valueOf(nextStep) + nextSequence;
    }
}
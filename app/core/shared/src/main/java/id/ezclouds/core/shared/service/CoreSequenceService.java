/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.shared.service;

import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.error.EzErrorCode;
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

    public String generateSequence(String orgId, String orgCode, String scene) {
        EzCoreSequenceDO currentSequence = ezCoreSequenceRepository.findForUpdateByOrgAndScene(orgId, scene);

        AssertUtil.notNull(currentSequence, EzErrorCode.CORE_SEQUENCE_ERROR, "EzCoreSequenceDO is null");

        int nextStep;
        int nextSequence = currentSequence.getSequence();
        if (currentSequence.getStepValue() >= currentSequence.getStepMax()) {
            nextStep = currentSequence.getStepMin();
            nextSequence++;
        } else {
            nextStep = currentSequence.getStepValue() + 1;
        }

        ezCoreSequenceRepository.updateEzCoreSequence(currentSequence.getSequenceId(), nextStep, nextSequence);

        String shard = String.valueOf(nextStep).substring(1);
        String sceneCode = currentSequence.getSceneCode();
        String sequence = composeSequence(nextSequence, currentSequence.getSequenceLength());

        return orgCode + shard + sceneCode + sequence;
    }

    private String composeSequence(int sequence, int sequenceLength) {
        String seqStr = String.valueOf(sequence);
        while (seqStr.length() < sequenceLength) {
            seqStr = "0" + seqStr;
        }

        return seqStr;
    }
}
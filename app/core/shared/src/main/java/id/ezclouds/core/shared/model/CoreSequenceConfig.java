/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.model;

import id.ezclouds.core.shared.repo.dataobject.EzCoreSequenceDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreSequenceConfig.java, v 0.1 2024‐03‐29 4:02 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CoreSequenceConfig {

    private String seqId;
    private String orgId;
    private String scene;
    private String sceneCode;
    private int stepMin;
    private int stepMax;
    private int stepValue;
    private int seqLength;
    private int sequence;

    public EzCoreSequenceDO toSequenceDO() {
        EzCoreSequenceDO sequenceDO = new EzCoreSequenceDO();
        sequenceDO.setSequenceId(seqId);
        sequenceDO.setOrgId(orgId);
        sequenceDO.setScene(scene);
        sequenceDO.setSceneCode(sceneCode);
        sequenceDO.setStepMin(stepMin);
        sequenceDO.setStepMax(stepMax);
        sequenceDO.setStepValue(stepValue);
        sequenceDO.setSequenceLength(seqLength);
        sequenceDO.setSequence(sequence);
        return sequenceDO;
    }

    public String getSeqId() {
        return seqId;
    }

    public void setSeqId(String seqId) {
        this.seqId = seqId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getSceneCode() {
        return sceneCode;
    }

    public void setSceneCode(String sceneCode) {
        this.sceneCode = sceneCode;
    }

    public int getStepMin() {
        return stepMin;
    }

    public void setStepMin(int stepMin) {
        this.stepMin = stepMin;
    }

    public int getStepMax() {
        return stepMax;
    }

    public void setStepMax(int stepMax) {
        this.stepMax = stepMax;
    }

    public int getStepValue() {
        return stepValue;
    }

    public void setStepValue(int stepValue) {
        this.stepValue = stepValue;
    }

    public int getSeqLength() {
        return seqLength;
    }

    public void setSeqLength(int seqLength) {
        this.seqLength = seqLength;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }
}
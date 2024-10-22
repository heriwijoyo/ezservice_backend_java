/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.core.sequence;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreSequence.java, v 0.1 2024‐09‐22 5:36 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CoreSequence {

    private String sequenceId;
    private String orgId;
    private CoreSeqScene seqScene;
    private int stepMin;
    private int stepMax;
    private int stepValue;
    private int seqLength;
    private int sequence;

    public String getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(String sequenceId) {
        this.sequenceId = sequenceId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public CoreSeqScene getSeqScene() {
        return seqScene;
    }

    public void setSeqScene(CoreSeqScene seqScene) {
        this.seqScene = seqScene;
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
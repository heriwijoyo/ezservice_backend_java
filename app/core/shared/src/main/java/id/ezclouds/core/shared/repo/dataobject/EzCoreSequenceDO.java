/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.shared.repo.dataobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzCoreSequenceDO.java, v 0.1 2023‐12‐30 3:51 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Entity
@Table(name = "ez_core_sequence")
public class EzCoreSequenceDO {

    @Id
    @Column(name = "sequence_id")
    private String sequenceId;

    @Column(name = "org_id")
    private String orgId;

    @Column(name = "scene")
    private String scene;

    @Column(name = "scene_code")
    private String sceneCode;

    @Column(name = "step_min")
    private int stepMin;

    @Column(name = "step_max")
    private int stepMax;

    @Column(name = "step_value")
    private int stepValue;

    @Column(name = "sequence_length")
    private int sequenceLength;

    @Column(name = "sequence")
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

    public int getSequenceLength() {
        return sequenceLength;
    }

    public void setSequenceLength(int sequenceLength) {
        this.sequenceLength = sequenceLength;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }
}